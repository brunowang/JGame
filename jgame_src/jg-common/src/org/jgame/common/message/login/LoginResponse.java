package org.jgame.common.message.login;

import org.jgame.common.message.MsgCode;
import org.jgame.common.net.msg.MsgData;
import org.msgpack.annotation.Message;

@Message
public class LoginResponse extends MsgData {

	private int code;
	
	public LoginResponse(){}
	public LoginResponse(int code) {
		this.setMsgId(MsgCode.LOGIN_RESP);
		
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
