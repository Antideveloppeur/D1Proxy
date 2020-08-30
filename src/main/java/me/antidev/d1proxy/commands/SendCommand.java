package me.antidev.d1proxy.commands;

import com.github.simplenet.packet.Packet;
import me.antidev.d1proxy.Proxy;
import me.antidev.d1proxy.network.ProxyClient;
import lombok.Getter;

import java.nio.charset.StandardCharsets;

public class SendCommand implements Command {

    @Getter
    private final String description = "[DEBUG] Envoie un packet";
    private final Proxy proxy;

    public SendCommand(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void execute(ProxyClient proxyClient, String args) {
        if (args.isBlank()) {
            proxyClient.sendMessage("<b>Utilisation</b> : " + proxy.getConfiguration().getProxyPrefix() + "send [packet]");
            return;
        }
        Packet.builder().putBytes(args.getBytes(StandardCharsets.UTF_8)).putByte(10).putByte(0).queueAndFlush(proxyClient.getServer());
    }
}
