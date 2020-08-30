package me.antidev.d1proxy.handlers;

import me.antidev.d1proxy.Proxy;
import me.antidev.d1proxy.network.ProxyClient;

public class ASKHandler implements PacketHandler {

    private final Proxy proxy;

    public ASKHandler(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public boolean shouldForward(ProxyClient proxyClient, String packet, PacketDestination destination) {
        String[] extraData = packet.split("\\|");
        proxyClient.setCharacterId(Integer.parseInt(extraData[1]));
        proxyClient.log("Setting username as: " + extraData[2]);
        proxyClient.setUsername(extraData[2]);
        if (proxy.getConfiguration().isMongoEnabled() && !proxy.getDatabase().getProfilesCollection().profileExists(extraData[2])) {
            proxy.getDatabase().getProfilesCollection().insertNewProfile(extraData[2]);
            proxy.sendMessage("<b>" + extraData[2] + "</b> est nouveau sur D1Proxy, bienvenue !");
            return true;
        } else {
            proxy.sendMessage("<b>" + extraData[2] + "</b> vient de se connecter à D1Proxy.");
        }
        return true;
    }
}
