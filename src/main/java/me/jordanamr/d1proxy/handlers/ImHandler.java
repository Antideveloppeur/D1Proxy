package me.jordanamr.d1proxy.handlers;

import me.jordanamr.d1proxy.Proxy;
import me.jordanamr.d1proxy.network.ProxyClient;
import me.jordanamr.d1proxy.network.ProxyClientState;
import com.github.simplenet.packet.Packet;

import java.nio.charset.StandardCharsets;

public class ImHandler implements PacketHandler {

    private final Proxy proxy;

    public ImHandler(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        if (packet.startsWith("Im0153;")) {
            proxyClient.setState(ProxyClientState.INGAME);
            Packet.builder().putBytes(packet.getBytes(StandardCharsets.UTF_8)).putByte(0).queueAndFlush(proxyClient.getClient());
            proxyClient.sendMessage("Bienvenue sur D1Proxy " + proxy.getVersion() + " !");
            return false;
        }
        return true;
    }
}
