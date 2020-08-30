package me.antidev.d1proxy.network;

public enum ProxyClientState {
    INITIALIZING,
    SERVER_SELECT,
    SERVER_CONNECTING,
    INGAME,
    DISCONNECTED
}