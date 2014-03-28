package com.probablycoding.bukkit.playersimulator;

import net.minecraft.server.v1_7_R1.EntityPlayer;
import net.minecraft.server.v1_7_R1.PlayerInteractManager;
import net.minecraft.server.v1_7_R1.PlayerList;
import net.minecraft.server.v1_7_R1.WorldServer;
import net.minecraft.util.com.google.common.base.Charsets;
import net.minecraft.util.com.mojang.authlib.GameProfile;

import org.bukkit.craftbukkit.v1_7_R1.CraftServer;
import org.bukkit.craftbukkit.v1_7_R1.CraftWorld;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


public class PlayerSimulator extends JavaPlugin implements Listener {
    private TPSCheck tpsCheck = new TPSCheck();
    private boolean toggle = false;

    @Override
    public void onEnable() {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, tpsCheck, 20, 20);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("spawnbot")) {
            int range = 2000;
            int num = 1;
            if (args.length > 0) {
                num = Integer.parseInt(args[0]);
            }
            if (args.length > 1) {
                range = Integer.parseInt(args[1]);
            }

            for (int i = 0; i < num; i++) {
                Random random = new Random();
                String name = ChatColor.BLUE + "Bot" + random.nextInt(1000) + i;
                WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
                PlayerList playerList = ((CraftServer) Bukkit.getServer()).getHandle();
                UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
                GameProfile gameProfile = new GameProfile(uuid.toString().replaceAll("-", ""), name);

                EntityPlayer entityplayer = new EntityPlayer(playerList.getServer(), world, gameProfile, new PlayerInteractManager(world));
                new DummyPlayerConnection(playerList.getServer(), new DummyNetworkManager(), entityplayer);

                entityplayer.spawnIn(world);
                entityplayer.playerInteractManager.a((WorldServer) entityplayer.world);
                entityplayer.playerInteractManager.b(world.getWorldData().getGameType());

                entityplayer.setPosition(random.nextInt(range * 2) - range, 100, random.nextInt(range * 2) - range);

                playerList.players.add(entityplayer);
                world.addEntity(entityplayer);
                playerList.a(entityplayer, null);

                sender.sendMessage("Added player " + entityplayer.getName() + ChatColor.RESET + " at " + entityplayer.locX + ", " + entityplayer.locY + ", " + entityplayer.locZ + ".");
            }

            return true;
        }

        if (command.getName().equalsIgnoreCase("killbots")) {
            PlayerList playerList = ((CraftServer) Bukkit.getServer()).getHandle();
            for (EntityPlayer entityplayer : (CopyOnWriteArrayList<EntityPlayer>) playerList.players) {
                if (entityplayer.getName().startsWith(ChatColor.BLUE + "Bot")) {
                    entityplayer.playerConnection.disconnect("");
                    sender.sendMessage("Disconnected " + entityplayer.getName());
                }
            }
        }

        if (command.getName().equalsIgnoreCase("debug")) {
            toggle = !toggle;
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
