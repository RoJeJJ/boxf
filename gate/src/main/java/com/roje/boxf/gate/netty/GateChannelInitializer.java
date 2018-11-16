package com.roje.boxf.gate.netty;

import com.roje.boxf.common.config.NettyProperties;
import com.roje.boxf.common.netty.DefaultChannelInitializer;
import io.netty.channel.ChannelPipeline;
import org.springframework.stereotype.Component;

@Component
public class GateChannelInitializer extends DefaultChannelInitializer {

    public GateChannelInitializer(NettyProperties nettyProperties) {
        super(nettyProperties);
    }

    @Override
    public void init(ChannelPipeline pipeline) {
        pipeline.addLast(new GateMessageHandler());
    }
}
