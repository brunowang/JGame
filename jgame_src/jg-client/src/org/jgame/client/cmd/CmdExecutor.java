package org.jgame.client.cmd;

import org.jgame.common.message.gateway2hall.login.LoginRequest;

import io.netty.channel.Channel;

public class CmdExecutor {

	public void executeCmd(Channel channel, String cmd) {
		String[] params = cmd.split(" ");
		String oper = params[0];
		if ("login".equals(oper)) {
			if (checkParams(params, 3)) {
				channel.writeAndFlush(new LoginRequest(params[1], params[2]));
			}
		} else {
			System.out.println("unknown command");
		}
	}
	
	private boolean checkParams(String[] params, int length) {
		if (params.length != length) {
			String oper = params[0];
			if ("login".equals(oper)) {
				System.out.println("params wrong, the correct format is: login $account $pwd");
			}
			return false;
		}
		return true;
	}
	
}
