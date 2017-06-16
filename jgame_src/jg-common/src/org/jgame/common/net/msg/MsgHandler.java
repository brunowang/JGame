package org.jgame.common.net.msg;

import java.util.HashMap;
import java.util.Map;

import org.jgame.common.DelegateMethod;

import com.lmax.disruptor.EventHandler;

public class MsgHandler implements EventHandler<Message> {
	
	private Map<Integer, MsgDelegate> delegates = new HashMap<>();
	
	@Override
	public void onEvent(Message msg, long sequence, boolean endOfBatch) throws Exception {
		MsgDelegate delegate = delegates.get(msg.getId());
		if (delegate != null) {
			delegate.doIt(msg.getChannel(), msg);
		} else {
			System.out.println("invalid message id:" + msg.getId());
		}
	}
	
	public void onRegister(int msgId, DelegateMethod method) {
		MsgDelegate delegate = delegates.get(msgId);
		if (delegate != null) {
			delegate.addMethod(method);
		} else {
			delegates.put(msgId, new MsgDelegate(method));
		}
	}

}
