package org.jgame.common.net.codec;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgDecoder extends MessageToMessageDecoder<ByteBuf> {

	public final int BASE_LENGTH = Integer.BYTES;
	public final int MAX_LENGTH = 5120;
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
		while (msg.readableBytes() >= BASE_LENGTH) {
//			System.out.println("MsgPackEncoder decode()");
			
			if (msg.readableBytes() > 2048) {
				msg.skipBytes(msg.readableBytes());
			}
			msg.markReaderIndex();
			
			final int length = msg.readInt();
			if (msg.readableBytes() < length) {
	            msg.resetReaderIndex();
	            return;
	        }
			final byte[] content = new byte[length];
			msg.readBytes(content);
			out.add(MsgSerializer.getInstance().read(content));
		}
	}

}
