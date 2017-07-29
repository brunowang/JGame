package org.jgame.common.net.codec;

import org.jgame.common.net.msg.MsgPacket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
//		System.out.println("MsgPackEncoder encode()");
		
		byte[] byteArray = MsgSerializer.getInstance().write(msg);
		MsgPacket packet = new MsgPacket(byteArray);
		packet.write(out);
//		out.writeBytes(byteArray);
//		byte[] endMark = {'$','_'};
//		out.writeBytes(endMark);
	}

}
