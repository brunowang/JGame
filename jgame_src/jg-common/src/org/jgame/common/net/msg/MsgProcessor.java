package org.jgame.common.net.msg;

import org.jgame.common.net.MsgDispatcher;

public abstract class MsgProcessor {
	
	public abstract void register();
	
	public void registerMethod(int msgId, MsgDelegateMethod method) {
		MsgDispatcher.getInstance().onRegister(msgId, method);
	}
}
