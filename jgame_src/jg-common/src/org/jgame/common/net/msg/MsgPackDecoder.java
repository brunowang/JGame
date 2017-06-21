package org.jgame.common.net.msg;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

import org.msgpack.MessagePack;

public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
		final int length = msg.readableBytes();
		final byte[] byteArray = new byte[length];
		msg.getBytes(0, byteArray, 0, length);
		MessagePack msgPack = new MessagePack();
		out.add(msgPack.read(byteArray));
	}

}
