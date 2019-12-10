package me.jordanamr.d1proxy.handlers;

import me.jordanamr.d1proxy.network.ProxyClient;
import com.github.simplenet.packet.Packet;

public class HGHandler implements PacketHandler {

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        Packet.builder().putBytes("AT".getBytes()).putBytes(proxyClient.getAuthTicket().getBytes()).putByte(10).putByte(0).queueAndFlush(proxyClient.getServer());
        return false;
    }
}
