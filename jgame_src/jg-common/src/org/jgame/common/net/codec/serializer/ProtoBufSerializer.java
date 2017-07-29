package org.jgame.common.net.codec.serializer;

import org.jgame.common.net.codec.ISerializer;

public class ProtoBufSerializer implements ISerializer {

	@Override
	public <T> T read(Class<T> clazz, Object data) {
		throw new RuntimeException("method not support yet");
	}

	@Override
	public Object read(byte[] data) {
		throw new RuntimeException("method not support yet");
	}

	@Override
	public byte[] write(Object msg) {
		throw new RuntimeException("method not support yet");
	}

}
