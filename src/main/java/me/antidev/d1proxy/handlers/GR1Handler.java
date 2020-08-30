package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.Proxy;
import me.antidev.d1proxy.network.ProxyClient;
import com.github.simplenet.packet.Packet;

public class GR1Handler implements PacketHandler {

    private final Proxy proxy;

    public GR1Handler(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        if (destination != PacketDestination.CLIENT || proxyClient.getGroupLeader() == 0) {
            return true;
        }
        ProxyClient client = proxy.getClientByCharacterId(Integer.parseInt(packet.substring(3)));
        if (client != null && client.isAutoReadyEnabled() && client.getCharacterId() != proxyClient.getCharacterId() && client.getGroupLeader() == proxyClient.getGroupLeader() && client.getIp().equals(proxyClient.getIp())) {
            Packet.builder().putBytes("GR1".getBytes()).putByte(10).putByte(0).queueAndFlush(proxyClient.getServer());
        }
        return true;
    }
}
