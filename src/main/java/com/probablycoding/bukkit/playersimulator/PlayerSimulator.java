package com.probablycoding.bukkit.playersimulator;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.ItemInWorldManager;
import net.minecraft.server.ServerConfigurationManager;
import net.minecraft.server.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
            int range = 2000;
            int num = 1;
            if (args.length > 0) {
                range = Integer.parseInt(args[0]);
            }
            if (args.length > 1) {
                num = Integer.parseInt(args[1]);
            }

            for (int i = 0; i < num; i++) {
                Random random = new Random();
                WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
                ServerConfigurationManager serverConfigurationManager = ((CraftServer) Bukkit.getServer()).getHandle();

                EntityPlayer entityplayer = new EntityPlayer(serverConfigurationManager.getServer(), world, ChatColor.BLUE + "Bot" + random.nextInt(1000) + i, new ItemInWorldManager(world));
                new DummyNetServerHandler(serverConfigurationManager.getServer(), new DummyNetworkManager(), entityplayer);

                entityplayer.spawnIn(world);
                entityplayer.itemInWorldManager.a((WorldServer) entityplayer.world);
                entityplayer.itemInWorldManager.b(world.getWorldData().getGameType());

                entityplayer.setPosition(random.nextInt(range * 2) - range, 100, random.nextInt(range * 2) - range);

                serverConfigurationManager.players.add(entityplayer);
                world.addEntity(entityplayer);
                serverConfigurationManager.a(entityplayer, (WorldServer) null);

                sender.sendMessage("Added player " + entityplayer.name + ChatColor.RESET + " at " + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ".");
            }

            return true;
        }

        if (command.getName().equalsIgnoreCase("killbots")) {
            ServerConfigurationManager serverConfigurationManager = ((CraftServer) Bukkit.getServer()).getHandle();
            for (EntityPlayer entityplayer : (CopyOnWriteArrayList<EntityPlayer>) serverConfigurationManager.players) {
                if (entityplayer.name.startsWith(ChatColor.BLUE + "Bot")) {
                    entityplayer.netServerHandler.disconnect("");
                    sender.sendMessage("Disconnected " + entityplayer.name);
                }
            }
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
