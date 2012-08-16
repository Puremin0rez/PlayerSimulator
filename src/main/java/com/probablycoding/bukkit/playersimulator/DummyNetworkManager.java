package com.probablycoding.bukkit.playersimulator;

import net.minecraft.server.*;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicInteger;

public class DummyNetworkManager implements INetworkManager {
    public static AtomicInteger a = new AtomicInteger();
    public static AtomicInteger b = new AtomicInteger();
    public Socket socket;
    public static int[] c = new int[256];
    public static int[] d = new int[256];
    public int e = 0;

    public DummyNetworkManager() {
    }

    public void a(NetHandler nethandler) {
    }

    public void queue(Packet packet) {
    }

    private boolean h() {
        return false;
    }

    public void a() {
    }

    public void a(String s, Object... aobject) {
    }

    public void b() {
    }

    public SocketAddress getSocketAddress() {
        return new SocketAddress() {
        };
    }

    public void d() {
    }

    public int e() {
        return 0;
    }

    public Socket getSocket() {
        return null;
    }
}
