package com.probablycoding.bukkit.playersimulator;

import java.util.Random;

import net.minecraft.server.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.plugin.java.JavaPlugin;


public class PlayerSimulator extends JavaPlugin {
    private TPSCheck tpsCheck = new TPSCheck();

    @Override
    public void onEnable() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, tpsCheck, 20, 20);
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("spawn")) {
            WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
            MinecraftServer server = ((CraftServer) Bukkit.getServer()).getHandle().getServer();
            EntityPlayer entityplayer = new EntityPlayer(server, world, Integer.toString(new Random().nextInt()), new ItemInWorldManager(world));
            new DummyNetServerHandler(server, new DummyNetworkManager(), entityplayer);

            entityplayer.spawnIn(server.getWorldServer(entityplayer.dimension));
            entityplayer.itemInWorldManager.a((WorldServer) entityplayer.world);

            WorldServer worldserver = server.getWorldServer(entityplayer.dimension);
            ChunkCoordinates chunkcoordinates = worldserver.getSpawn();

            entityplayer.itemInWorldManager.b(world.getWorldData().getGameType());

            ((CraftServer) Bukkit.getServer()).getHandle().players.add(entityplayer);

            entityplayer.setPosition(new Random().nextInt(500000) - 250000, 100, new Random().nextInt(500000) - 250000);

            worldserver.addEntity(entityplayer);

            ((CraftServer) Bukkit.getServer()).getHandle().a(entityplayer, (WorldServer) null);

            sender.sendMessage("Added player " + entityplayer.name + " at " + entityplayer.locX + "," + entityplayer.locY + "," + entityplayer.locZ + ".");
            return true;
        }

        if (command.getName().equalsIgnoreCase("debug")) {
            float tps = 0;
            for (Long l : tpsCheck.history) {
                if (l != null)
                    tps += 20 / (l / 1000);
            }
            tps = tps / tpsCheck.history.size();

            sender.sendMessage("TPS: " + tps + " Loaded chunks: " + Bukkit.getWorlds().get(0).getLoadedChunks().length + " Entities: " + Bukkit.getWorlds().get(0).getEntities().size());
        }
        return false;
    }
}
