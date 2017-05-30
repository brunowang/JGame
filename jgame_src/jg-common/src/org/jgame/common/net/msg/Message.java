package org.jgame.common.net.msg;

public class Message {
	private String data;
	public void setData(String data) {
		this.data = data;
	}
	public String getData() {
		return data;
	}
	public Message(){}
	public Message(String data) {
		this.data = data;
	}
}
