package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.network.ProxyClient;

public class PLHandler implements PacketHandler {

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        proxyClient.setGroupLeader(Integer.parseInt(packet.substring(2)));
        return true;
    }
}
