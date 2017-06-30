package org.jgame.client.response;

import org.jgame.client.constant.MsgCode;
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
