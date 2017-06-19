package org.jgame.common.net;

import org.jgame.common.net.msg.Message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelHandler extends ChannelInboundHandlerAdapter {


	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server channel active...");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		MsgDispatcher.getInstance().onReceiveMsg(new Message(ctx, (String)msg));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t) throws Exception {
		ctx.close();
	}




}
