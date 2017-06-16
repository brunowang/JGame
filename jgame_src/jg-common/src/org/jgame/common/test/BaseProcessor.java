package org.jgame.common.test;

import org.jgame.common.DelegateMethod;
import org.jgame.common.net.MsgDispatcher;

public abstract class BaseProcessor {
	
	public abstract BaseProcessor init();
	
	public void registerMethod(int msgId, DelegateMethod method) {
		MsgDispatcher.getInstance().onRegister(msgId, method);
	}
}
