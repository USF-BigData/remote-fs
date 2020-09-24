package edu.usfca.cs.rfs.net;

import edu.usfca.cs.rfs.RfsMessages;

import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class MessagePipeline extends ChannelInitializer<SocketChannel> {

    private ChannelInboundHandlerAdapter inboundHandler;

    public MessagePipeline(ChannelInboundHandlerAdapter inboundHandler) {
        this.inboundHandler = inboundHandler;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new LengthFieldBasedFrameDecoder(1048576, 0, 4, 0, 4));
        pipeline.addLast(
                new ProtobufDecoder(
                    RfsMessages.RfsMessageWrapper.getDefaultInstance()));

        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(inboundHandler);
    }
}
