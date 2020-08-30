package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.network.ProxyClient;

public class AYKHandler implements PacketHandler {

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        String[] extraData = packet.substring(3).split(";");
        proxyClient.setAuthTicket(extraData[1]);
        if (extraData[0].contains(":")) {
            String[] address = extraData[0].split(":");
            proxyClient.switchToGame(address[0], Integer.parseInt(address[1]));
        } else {
            proxyClient.switchToGame(extraData[0], 443);
        }
        return false;
    }

}
