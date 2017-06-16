package org.jgame.common.test;

import org.jgame.common.net.MsgDispatcher;

public class ServerContext {
	
	public void init() {
		MsgDispatcher.getInstance().init();
		
		new LoginProcessor().init();
		
		System.out.println("server context init success...");
	}
}
