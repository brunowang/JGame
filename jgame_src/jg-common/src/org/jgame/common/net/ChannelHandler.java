package org.jgame.common.net;

import org.jgame.common.net.msg.Message;
import org.jgame.common.session.PlayerSession;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ChannelHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("server channel active... from address: " + ctx.channel().remoteAddress());
		PlayerSession session = PlayerSession.getPlayerSession(ctx);
		if (session == null) {
			new PlayerSession(ctx);
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		PlayerSession session = PlayerSession.getPlayerSession(ctx);
		if (session == null) {
			return;
		}
		MsgDispatcher.getInstance().onReceiveMsg(new Message(session, msg));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable t)
			throws Exception {
		ctx.close();
	}

}
