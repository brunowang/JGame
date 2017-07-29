package org.jgame.hall.processor;

import org.jgame.common.message.gateway2hall.MsgCode;
import org.jgame.common.message.gateway2hall.login.LoginRequest;
import org.jgame.common.message.gateway2hall.login.LoginResponse;
import org.jgame.common.net.codec.MsgSerializer;
import org.jgame.common.net.msg.Message;
import org.jgame.common.net.msg.MsgProcessor;
import org.jgame.common.session.PlayerSession;
import org.jgame.hall.constant.ErrorCode;

public class LoginProcessor extends MsgProcessor {

	@Override
	public void register() {
		this.registerMethod(MsgCode.LOGIN_REQ, (session, msg)->login(session, msg));
		this.registerMethod(MsgCode.LOGOUT_REQ, (session, msg)->logout(session, msg));
	}
	
	private void login(PlayerSession session, Message msg) {
		LoginRequest req = MsgSerializer.getInstance().read(LoginRequest.class, msg.getData());
		
		System.out.println("login request account: " + req.getAccount() + ", pwd:" + req.getPwd());
		
		session.sendMsg(new LoginResponse(ErrorCode.SUCCESS));
	}
	
	private void logout(PlayerSession session, Message msg) {
		System.out.println("just for test...");
	}

}
