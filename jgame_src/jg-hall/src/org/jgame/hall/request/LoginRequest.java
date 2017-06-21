package org.jgame.hall.request;

import org.jgame.common.net.msg.MsgData;
import org.msgpack.annotation.Message;

@Message
public class LoginRequest extends MsgData {

	private String account;
	private String pwd;
	
	public LoginRequest(){}
	public LoginRequest(String account, String pwd) {
		this.setMsgId(10001);
		
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
