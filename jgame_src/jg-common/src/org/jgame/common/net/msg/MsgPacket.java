package org.jgame.common.net.msg;

import io.netty.buffer.ByteBuf;

public class MsgPacket {
	private int length;
	private byte[] content;
	public MsgPacket(byte[] content) {
		this.length = content.length;
		this.content = content;
	}
	public void write(ByteBuf out) {
		out.writeInt(length);
		out.writeBytes(content);
	}
}
