package org.jgame.client.response;

import org.jgame.common.net.msg.MsgData;
import org.msgpack.annotation.Message;

@Message
public class LoginResponse extends MsgData {

	private int code;
	
	public LoginResponse(){}
	public LoginResponse(int code) {
		this.setMsgId(10002);
		
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
