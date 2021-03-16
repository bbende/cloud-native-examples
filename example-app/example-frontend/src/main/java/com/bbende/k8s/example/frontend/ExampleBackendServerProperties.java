package com.bbende.k8s.example.frontend;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "example.app.backend.server", ignoreUnknownFields = false)
public class ExampleBackendServerProperties {

    private String protocol;
    private String host;
    private String port;
    private String messagesContext;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getMessagesContext() {
        return messagesContext;
    }

    public void setMessagesContext(String messagesContext) {
        this.messagesContext = messagesContext;
    }
}
