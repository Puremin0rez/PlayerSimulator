package com.probablycoding.bukkit.playersimulator;

import net.minecraft.server.v1_7_R2.EntityPlayer;
import net.minecraft.server.v1_7_R2.EnumProtocol;
import net.minecraft.server.v1_7_R2.IChatBaseComponent;
import net.minecraft.server.v1_7_R2.MinecraftServer;
import net.minecraft.server.v1_7_R2.NetworkManager;
import net.minecraft.server.v1_7_R2.Packet;
import net.minecraft.server.v1_7_R2.PacketPlayInAbilities;
import net.minecraft.server.v1_7_R2.PacketPlayInArmAnimation;
import net.minecraft.server.v1_7_R2.PacketPlayInBlockDig;
import net.minecraft.server.v1_7_R2.PacketPlayInBlockPlace;
import net.minecraft.server.v1_7_R2.PacketPlayInChat;
import net.minecraft.server.v1_7_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_7_R2.PacketPlayInCloseWindow;
import net.minecraft.server.v1_7_R2.PacketPlayInCustomPayload;
import net.minecraft.server.v1_7_R2.PacketPlayInEnchantItem;
import net.minecraft.server.v1_7_R2.PacketPlayInEntityAction;
import net.minecraft.server.v1_7_R2.PacketPlayInFlying;
import net.minecraft.server.v1_7_R2.PacketPlayInHeldItemSlot;
import net.minecraft.server.v1_7_R2.PacketPlayInKeepAlive;
import net.minecraft.server.v1_7_R2.PacketPlayInSetCreativeSlot;
import net.minecraft.server.v1_7_R2.PacketPlayInSettings;
import net.minecraft.server.v1_7_R2.PacketPlayInSteerVehicle;
import net.minecraft.server.v1_7_R2.PacketPlayInTabComplete;
import net.minecraft.server.v1_7_R2.PacketPlayInTransaction;
import net.minecraft.server.v1_7_R2.PacketPlayInUpdateSign;
import net.minecraft.server.v1_7_R2.PacketPlayInUseEntity;
import net.minecraft.server.v1_7_R2.PacketPlayInWindowClick;
import net.minecraft.server.v1_7_R2.PlayerConnection;
import net.minecraft.server.v1_7_R2.WorldServer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R2.CraftServer;
import org.bukkit.craftbukkit.v1_7_R2.entity.CraftPlayer;

public class DummyPlayerConnection extends PlayerConnection {
    private boolean disconnected = false;

    public DummyPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

    @Override
    public CraftPlayer getPlayer() {
        return (this.player == null) ? null : this.player.getBukkitEntity();
    }

    @Override
    public void a() {
    }

    public NetworkManager b() {
        return this.networkManager;
    }

    public void disconnect(java.lang.String s) {
        WorldServer worldserver = player.r();

        worldserver.kill(player);
        worldserver.getPlayerChunkMap().removePlayer(player);
        ((CraftServer) Bukkit.getServer()).getHandle().players.remove(player);
        this.disconnected = true;
    }

    public void a(PacketPlayInSteerVehicle packetplayinsteervehicle) {
    }

    public void a(PacketPlayInFlying packetplayinflying) {
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
    }

    public void teleport(Location dest) {
    }

    public void a(PacketPlayInBlockDig packetplayinblockdig) {
    }

    public void a(PacketPlayInBlockPlace packetplayinblockplace) {
    }

    public void a(IChatBaseComponent ichatbasecomponent) {
    }

    public void sendPacket(Packet packet) {
    }

    public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {
    }

    public void a(PacketPlayInChat packetplayinchat) {
    }

    public void chat(String s, boolean async) {
    }

    public void a(PacketPlayInArmAnimation packetplayinarmanimation) {
    }

    public void a(PacketPlayInEntityAction packetplayinentityaction) {
    }

    public void a(PacketPlayInUseEntity packetplayinuseentity) {
    }

    public void a(PacketPlayInClientCommand packetplayinclientcommand) {
    }

    public void a(PacketPlayInCloseWindow packetplayinclosewindow) {
    }

    public void a(PacketPlayInWindowClick packetplayinwindowclick) {
    }

    public void a(PacketPlayInEnchantItem packetplayinenchantitem) {
    }

    public void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot) {
    }

    public void a(PacketPlayInTransaction packetplayintransaction) {
    }

    public void a(PacketPlayInUpdateSign packetplayinupdatesign) {
    }

    public void a(PacketPlayInKeepAlive packetplayinkeepalive) {
    }

    public void a(PacketPlayInAbilities packetplayinabilities) {
    }

    public void a(PacketPlayInTabComplete packetplayintabcomplete) {
    }

    public void a(PacketPlayInSettings packetplayinsettings) {
    }

    public void a(PacketPlayInCustomPayload packetplayincustompayload) {
    }

    public void a(EnumProtocol enumprotocol, EnumProtocol enumprotocol1) {
    }
}
