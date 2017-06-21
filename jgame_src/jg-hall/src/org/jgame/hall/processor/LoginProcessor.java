package org.jgame.hall.processor;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import org.jgame.common.net.msg.Message;
import org.jgame.common.net.msg.MsgProcessor;
import org.jgame.hall.request.LoginRequest;
import org.jgame.hall.response.LoginResponse;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

public class LoginProcessor extends MsgProcessor {

	@Override
	public void register() {
		this.registerMethod(10001, (ctx, msg)->login(ctx, msg));
	}
	
	private void login(ChannelHandlerContext ctx, Message msg) {
		Value data = msg.getData();
		MessagePack messagePack = new MessagePack();
//		messagePack.register(LoginRequest.class);	//LoginRequest有@Message注解,就不需注册了
		LoginRequest req = null;
		try {
			req = new Converter(messagePack, data).read(LoginRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("msgId:" + req.getMsgId());
		System.out.println("login request account: " + req.getAccount() + ", pwd:" + req.getPwd());
		
		ctx.writeAndFlush(new LoginResponse(200));
//		String response = req.getAccount() + " login success!!!$_";
//		ctx.writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));
	}

}
