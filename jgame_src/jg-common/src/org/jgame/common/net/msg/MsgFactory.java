package org.jgame.common.net.msg;

import com.lmax.disruptor.EventFactory;

public class MsgFactory implements EventFactory<Message>{

	@Override
	public Message newInstance() {
		return new Message();
	}

}
