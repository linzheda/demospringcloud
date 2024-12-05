package com.linzd.basecore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 描述  获取url
 *
 * @author Lorenzo Lin
 * @created 2019年11月27日 19:58
 */
@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;
    @Value("${server.servlet.context-path:#{null}}")
    private String projectName;

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return "http://"+address.getHostAddress() +":"+this.serverPort+projectName;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
    }
}
