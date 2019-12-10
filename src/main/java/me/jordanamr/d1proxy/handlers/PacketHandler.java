package me.jordanamr.d1proxy.handlers;

import me.jordanamr.d1proxy.network.ProxyClient;

public interface PacketHandler {
    boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination);
}
