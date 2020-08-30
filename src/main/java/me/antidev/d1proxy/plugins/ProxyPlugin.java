package me.antidev.d1proxy.plugins;

import me.antidev.d1proxy.Proxy;
import org.pf4j.ExtensionPoint;

public interface ProxyPlugin extends ExtensionPoint {
    void main(Proxy proxy);
    String getPrettyName();
    String getVersion();
}