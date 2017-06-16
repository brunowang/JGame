package org.jgame.common.test;

import org.jgame.common.net.msg.Message;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class LoginProcessor extends BaseProcessor {

	@Override
	public BaseProcessor init() {
		this.registerMethod(10001, (ctx, msg)->login(ctx, msg));
		return this;
	}
	
	private void login(ChannelHandlerContext ctx, Message msg) {
		String[] params = msg.getData().split("__");
		System.out.println("msgId:" + params[0]);
		System.out.println("login request account: " + params[1] + ", pwd:" + params[2]);
		
		String response = params[1] + " login success!!!$_";
		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

}
