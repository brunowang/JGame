package org.jgame.common.net.msg;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgPackEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
//		System.out.println("MsgPackEncoder encode()");
		
		MessagePack msgPack = new MessagePack();
		byte[] byteArray = msgPack.write(msg);
		MsgPacket packet = new MsgPacket(byteArray);
		packet.write(out);
//		out.writeBytes(byteArray);
//		byte[] endMark = {'$','_'};
//		out.writeBytes(endMark);
	}

}
