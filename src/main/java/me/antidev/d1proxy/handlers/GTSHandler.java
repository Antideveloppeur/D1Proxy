package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.network.ProxyClient;
import com.github.simplenet.packet.Packet;

public class GTSHandler implements PacketHandler {

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        String[] extraData = packet.substring(3).split("\\|");
        if (proxyClient.isAutoSkipEnabled() && proxyClient.getCharacterId() == Integer.parseInt(extraData[0])) {
            Packet.builder().putBytes("Gt".getBytes()).putByte(10).putByte(0).queueAndFlush(proxyClient.getServer());
            proxyClient.sendMessage("Tour passé automatiquement");
        }
        return true;
    }
}
