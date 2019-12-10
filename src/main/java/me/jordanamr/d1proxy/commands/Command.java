package me.jordanamr.d1proxy.commands;

import me.jordanamr.d1proxy.network.ProxyClient;

public interface Command {
    String getDescription();
    void execute(ProxyClient proxyClient, String args);
}
