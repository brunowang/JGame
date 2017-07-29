package org.jgame.common.message.login;

import org.jgame.common.message.MsgCode;
import org.jgame.common.net.msg.MsgData;
import org.msgpack.annotation.Message;

@Message
public class LoginRequest extends MsgData {

	private String account;
	private String pwd;
	
	public LoginRequest(){}
	public LoginRequest(String account, String pwd) {
		this.setMsgId(MsgCode.LOGIN_REQ);
		
		this.account = account;
		this.pwd = pwd;
	}
	
	public String getAccount() {
		return account;
	}
	public String getPwd() {
		return pwd;
	}

}
