package org.jgame.common.net.msg;

import java.io.IOException;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

public class MsgSerializer {
	private static final MsgSerializer instance = new MsgSerializer();
	
	private MessagePack messagePack = new MessagePack();
	
	private MsgSerializer(){}
	
	public static MsgSerializer getInstance() {
		return instance;
	}
	
	public void init() {
//		messagePack.register(MsgData.class);	//MsgData有@Message注解,就不需注册了
//		messagePack.register(LoginRequest.class);	//LoginRequest有@Message注解,就不需注册了
	}
	
	public <T> T read(Class<T> clazz, Value data) {
		T msgData = null;
		Converter converter = new Converter(messagePack, data);
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
}
