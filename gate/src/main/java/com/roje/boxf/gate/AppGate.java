package com.roje.boxf.gate;

import com.roje.boxf.common.config.NettyProperties;
import com.roje.boxf.common.netty.NettyServer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@Import(value = {
        NettyProperties.class
})
public class AppGate {
    public static void main(String[] args) {
        SpringApplication.run(AppGate.class,args);
    }

    @Bean
    public NettyServer nettyServer(ChannelInitializer<SocketChannel> channelInitializer,
                                   NettyProperties properties){
        return new NettyServer(channelInitializer,properties);
    }
}
