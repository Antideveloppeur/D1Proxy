package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.network.ProxyClient;

public interface PacketHandler {
    boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination);
}
