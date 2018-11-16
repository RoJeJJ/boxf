package com.roje.boxf.gate.netty;

import com.roje.boxf.proto.frame.Frame;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class GateMessageHandler extends SimpleChannelInboundHandler<Frame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Frame msg) throws Exception {

    }
}
