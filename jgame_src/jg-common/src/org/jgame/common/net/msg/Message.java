package org.jgame.common.net.msg;

import io.netty.channel.ChannelHandlerContext;

public class Message {
	private ChannelHandlerContext channel;
	private int id;
	private String data;
	
	public ChannelHandlerContext getChannel() {
		return channel;
	}
	public int getId() {
		return id;
	}
	public String getData() {
		return data;
	}
	
	public void copyFrom(Message msg) {
		this.channel = msg.getChannel();
		this.id = msg.getId();
		this.data = msg.getData();
	}
	
	public Message(){}
	public Message(ChannelHandlerContext channel, String data) {
		this.channel = channel;
		this.id = Integer.parseInt(data.split("__")[0]);
		this.data = data;
	}
}
