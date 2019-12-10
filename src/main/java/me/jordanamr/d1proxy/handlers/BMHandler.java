package me.jordanamr.d1proxy.handlers;

import me.jordanamr.d1proxy.Proxy;
import me.jordanamr.d1proxy.network.ProxyClient;
import com.github.simplenet.packet.Packet;

public class BMHandler implements PacketHandler {

    private final Proxy proxy;

    public BMHandler(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        String message = packet.split("\\|")[1];
        if (message.startsWith(proxy.getConfiguration().getProxyPrefix())) {
            proxyClient.log("Executing command " + message);
            Packet.builder().putBytes("BN".getBytes()).putByte(0).queueAndFlush(proxyClient.getClient());
            if (!proxyClient.executeCommand(message.substring(1))) {
                proxyClient.sendMessage("Commande introuvable. Tapez <b>" + proxy.getConfiguration().getProxyPrefix() + "help</b> pour obtenir la liste des commandes.");
            }
            return false;
        }
        proxyClient.log("Chat: " + message);
        return true;
    }
}
