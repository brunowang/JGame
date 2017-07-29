package org.jgame.common.net.codec.serializer;

import java.io.IOException;

import org.jgame.common.net.codec.ISerializer;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

public class MessagePackSerializer implements ISerializer {

	private MessagePack messagePack = new MessagePack();
	
	public void init() {
//		messagePack.register(MsgData.class);	//MsgData有@Message注解,就不需注册了
//		messagePack.register(LoginRequest.class);	//LoginRequest有@Message注解,就不需注册了
	}
	
	@Override
	public <T> T read(Class<T> clazz, Object data) {
		T msgData = null;
		Converter converter = new Converter(messagePack, (Value)data);
		try {
			msgData = converter.read(clazz);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				converter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return msgData;
	}
	
	@Override
	public Object read(byte[] data) {
		Value val = null;
		try {
			val = messagePack.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return val;
	}

	@Override
	public byte[] write(Object msg) {
		byte[] data = null;
		try {
			data = messagePack.write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

}
