package com.roje.boxf.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "netty")
@Getter@Setter
public class NettyProperties {

    @Value("server.port")
    private int port;

    private int soBacklog = 1024;

    private boolean tcpNoDelay = true;

    private boolean soKeepAlive = true;

    private int soLinger = 0;

    private int readerIdleTimeSeconds = 0;

    private int writerIdleTimeSeconds = 0;

    private int allIdleTimeSeconds = 0;
}
