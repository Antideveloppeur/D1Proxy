package me.jordanamr.d1proxy.handlers;

import me.jordanamr.d1proxy.network.ProxyClient;
import me.jordanamr.d1proxy.network.ProxyClientState;

public class AxHandler implements PacketHandler {

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        proxyClient.setState(ProxyClientState.SERVER_SELECT);
        return true;
    }
}
