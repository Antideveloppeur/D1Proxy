package me.antidev.d1proxy.commands;

import me.antidev.d1proxy.Proxy;
import me.antidev.d1proxy.network.ProxyClient;
import lombok.Getter;

public class AutojoinCommand implements Command {

    @Getter
    private String description = "Vos mules rejoignent vos combats automatiquement";
    private final Proxy proxy;

    public AutojoinCommand(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void execute(ProxyClient proxyClient, String args) {
        if (args.isBlank()) {
            proxyClient.sendMessage("<b>Utilisation :</b> " + proxy.getConfiguration().getProxyPrefix() + "autojoin on/off");
            return;
        }
        if (args.split(" ")[0].toLowerCase().equalsIgnoreCase("off")) {
            proxyClient.sendMessage(proxyClient.isAutoJoinEnabled() ? "Le mode autojoin est désormais <b>désactivé</b>" : "Vous n'êtes pas en mode autojoin");
            proxyClient.setAutoJoinEnabled(false);
        } else if (args.split(" ")[0].toLowerCase().equalsIgnoreCase("on")) {
            proxyClient.sendMessage(proxyClient.isAutoJoinEnabled() ? "Vous êtes déjà en mode autojoin" : "Le mode autojoin est désormais <b>activé</b>");
            proxyClient.setAutoJoinEnabled(true);
        } else {
            proxyClient.sendMessage("<b>Utilisation :</b> " + proxy.getConfiguration().getProxyPrefix() + "autojoin on/off");
        }
    }
}
