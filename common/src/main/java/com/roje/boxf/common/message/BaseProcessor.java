package com.roje.boxf.common.message;

import com.roje.boxf.common.message.dispatcher.MessageDispatcher;
import com.roje.boxf.proto.frame.Frame;
import io.netty.channel.ChannelHandlerContext;


public abstract class BaseProcessor {
    private final MessageDispatcher dispatcher;

    public BaseProcessor(MessageDispatcher dispatcher){
        this.dispatcher = dispatcher;
    }

    public void init(){
    }

    public abstract void process(ChannelHandlerContext ctx, Frame frame) throws Exception;

}
