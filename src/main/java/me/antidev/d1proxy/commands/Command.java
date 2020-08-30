package me.antidev.d1proxy.commands;

import me.antidev.d1proxy.network.ProxyClient;

public interface Command {
    String getDescription();
    void execute(ProxyClient proxyClient, String args);
}
