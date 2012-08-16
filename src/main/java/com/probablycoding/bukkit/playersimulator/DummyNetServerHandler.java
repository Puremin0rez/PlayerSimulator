package com.probablycoding.bukkit.playersimulator;

import net.minecraft.server.*;
import org.bukkit.*;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.craftbukkit.CraftServer;

public class DummyNetServerHandler extends NetServerHandler
{
    public DummyNetServerHandler(MinecraftServer minecraftserver, INetworkManager inetworkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, inetworkmanager, entityplayer);
    }

    @Override
    public CraftPlayer getPlayer() {
        return (this.player == null) ? null : (CraftPlayer) this.player.getBukkitEntity();
    }

    @Override
    public void d() {
    }

    @Override
    public void disconnect(String s) {
        ((CraftServer) Bukkit.getServer()).getHandle().players.remove(player);
    }

    @Override
    public void a(Packet10Flying packet10flying) {
    }

    @Override
    public void a(double d0, double d1, double d2, float f, float f1) {
    }

    @Override
    public void teleport(Location dest) {
    }

    @Override
    public void a(Packet14BlockDig packet14blockdig) {
    }

    @Override
    public void a(Packet15Place packet15place) {
    }

    @Override
    public void a(String s, Object[] aobject) {
    }

    @Override
    public void onUnhandledPacket(Packet packet) {
    }

    @Override
    public void sendPacket(Packet packet) {
    }

    @Override
    public void a(Packet16BlockItemSwitch packet16blockitemswitch) {
    }

    @Override
    public void a(Packet3Chat packet3chat) {
    }

    @Override
    public void chat(String s, boolean async) {
    }

    @Override
    public void a(Packet18ArmAnimation packet18armanimation) {
    }

    @Override
    public void a(Packet19EntityAction packet19entityaction) {
    }

    @Override
    public void a(Packet255KickDisconnect packet255kickdisconnect) {
    }

    @Override
    public int lowPriorityCount() {
        return 0;
    }

    @Override
    public void a(Packet7UseEntity packet7useentity) {
    }

    @Override
    public void a(Packet205ClientCommand packet205clientcommand) {
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public void a(Packet9Respawn packet9respawn) {}

    @Override
    public void handleContainerClose(Packet101CloseWindow packet101closewindow) {
    }

    @Override
    public void a(Packet102WindowClick packet102windowclick) {
    }

    @Override
    public void a(Packet108ButtonClick packet108buttonclick) {
    }

    @Override
    public void a(Packet107SetCreativeSlot packet107setcreativeslot) {
    }

    @Override
    public void a(Packet106Transaction packet106transaction) {
    }

    @Override
    public void a(Packet130UpdateSign packet130updatesign) {
    }

    @Override
    public void a(Packet0KeepAlive packet0keepalive) {
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public void a(Packet202Abilities packet202abilities) {
    }

    @Override
    public void a(Packet203TabComplete packet203tabcomplete) {
    }

    @Override
    public void a(Packet204LocaleAndViewDistance packet204localeandviewdistance) {
    }

    @Override
    public void a(Packet250CustomPayload packet250custompayload) {
    }
}
