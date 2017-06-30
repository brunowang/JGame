package org.jgame.common.session;

import org.jgame.common.net.msg.MsgData;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

public class PlayerSession {
	public static final AttributeKey<PlayerSession> KEY_PLAYER_SESSION = AttributeKey.valueOf("session.player");
	
	private volatile ChannelHandlerContext ctx = null;
	
	public PlayerSession(ChannelHandlerContext ctx) {
		this.ctx = ctx;
		ctx.channel().attr(KEY_PLAYER_SESSION).setIfAbsent(this);
	}
	
	public void sendMsg(MsgData msg) {
		ctx.writeAndFlush(msg);
	}
	
	public static PlayerSession getPlayerSession(ChannelHandlerContext ctx) {
		return ctx.channel().attr(KEY_PLAYER_SESSION).get();
	}
}
