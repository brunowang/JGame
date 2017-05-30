package org.jgame.common.net.msg;

import com.lmax.disruptor.EventHandler;

public class MsgHandler implements EventHandler<Message> {

	@Override
	public void onEvent(Message msg, long sequence, boolean endOfBatch) throws Exception {
		System.out.println(msg.getData());
	}

}
