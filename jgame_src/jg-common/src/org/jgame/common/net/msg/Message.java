package org.jgame.common.net.msg;

import org.jgame.common.net.codec.MsgSerializer;
import org.jgame.common.session.PlayerSession;

public class Message {
	private PlayerSession session;
	private Object data;
	private int id;
	
	public PlayerSession getSession() {
		return session;
	}
	public Object getData() {
		return data;
	}
	public int getId() {
		return id;
	}
	
	public void copyFrom(Message msg) {
		this.session = msg.getSession();
		this.data = msg.getData();
		this.id = msg.getId();
	}
	
	public Message(){}
	public Message(PlayerSession session, Object data) {
		this.session = session;
		this.data = data;
		MsgData msg = MsgSerializer.getInstance().read(MsgData.class, data);
		this.id = msg.getMsgId();
	}
}
