package com.roje.boxf.common.netty;

import com.roje.boxf.common.config.NettyProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

@Slf4j
public class NettyServer implements Runnable {

    private final ChannelInitializer<SocketChannel> channelInitializer;

    private final NettyProperties properties;

    public NettyServer(ChannelInitializer<SocketChannel> channelInitializer,
                       NettyProperties properties) {
        this.channelInitializer = channelInitializer;
        this.properties = properties;
    }

    @PostConstruct
    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,properties.getSoBacklog())
                    .option(ChannelOption.TCP_NODELAY,properties.isTcpNoDelay())
                    .option(ChannelOption.SO_KEEPALIVE,properties.isSoKeepAlive())
                    .option(ChannelOption.SO_LINGER,properties.getSoLinger())
                    .childHandler(channelInitializer);
            ChannelFuture future = bootstrap.bind(properties.getPort()).sync();
            future.channel().closeFuture().sync();
        }catch (InterruptedException e){
            log.warn("InterruptedException",e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
