package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.network.ProxyClient;
import me.antidev.d1proxy.network.ProxyClientState;

public class AxHandler implements PacketHandler {

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        proxyClient.setState(ProxyClientState.SERVER_SELECT);
        return true;
    }
}
