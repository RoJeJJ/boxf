package com.roje.boxf.common.netty;

import com.roje.boxf.common.config.NettyProperties;
import com.roje.boxf.proto.frame.Frame;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

public abstract class DefaultChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final NettyProperties nettyProperties;

    public DefaultChannelInitializer(NettyProperties nettyProperties) {
        this.nettyProperties = nettyProperties;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(nettyProperties.getReaderIdleTimeSeconds(),
                nettyProperties.getWriterIdleTimeSeconds(),nettyProperties.getAllIdleTimeSeconds()));
        pipeline.addLast("frameDecoder",new ProtobufVarint32FrameDecoder());
        pipeline.addLast("protobufDecoder",new ProtobufDecoder(Frame.getDefaultInstance()));
        pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("protobufEncoder", new ProtobufEncoder());
        init(pipeline);

    }

    public abstract void init(ChannelPipeline pipeline);
}
