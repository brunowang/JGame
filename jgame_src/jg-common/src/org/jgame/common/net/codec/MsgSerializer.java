package org.jgame.common.net.codec;

import org.jgame.common.net.codec.serializer.MessagePackSerializer;

public class MsgSerializer {
	
	private final ISerializer msgSerializer;
	
	private static final MsgSerializer instance = new MsgSerializer();
	
	private MsgSerializer(){
		msgSerializer = new MessagePackSerializer();
	}
	
	public static MsgSerializer getInstance() {
		return instance;
	}
	
	public <T> T read(Class<T> clazz, Object data) {
		return msgSerializer.read(clazz, data);
	}
	
	public Object read(byte[] data) {
		return msgSerializer.read(data);
	}
	
	public byte[] write(Object msg) {
		return msgSerializer.write(msg);
	}
	
}
