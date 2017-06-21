package org.jgame.common.net.msg;

import org.msgpack.annotation.Message;

@Message
public class MsgData {
	private int msgId;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
}
