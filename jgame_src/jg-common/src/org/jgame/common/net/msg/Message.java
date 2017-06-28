package org.jgame.common.net.msg;

import java.io.IOException;

import org.msgpack.MessagePack;
import org.msgpack.type.Value;
import org.msgpack.unpacker.Converter;

import io.netty.channel.ChannelHandlerContext;

public class Message {
	private ChannelHandlerContext channel;
	private Value data;
	private int id;
	
	public ChannelHandlerContext getChannel() {
		return channel;
	}
	public Value getData() {
		return data;
	}
	public int getId() {
		return id;
	}
	
	public void copyFrom(Message msg) {
		this.channel = msg.getChannel();
		this.data = msg.getData();
		this.id = msg.getId();
	}
	
	public Message(){}
	public Message(ChannelHandlerContext channel, Value data) {
		this.channel = channel;
		this.data = data;
		
		MessagePack messagePack = new MessagePack();
//		messagePack.register(MsgData.class);	//MsgData有@Message注解,就不需注册了
		MsgData msg;
		Converter converter = new Converter(messagePack, data);
		try {
			msg = converter.read(MsgData.class);
			this.id = msg.getMsgId();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				converter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
