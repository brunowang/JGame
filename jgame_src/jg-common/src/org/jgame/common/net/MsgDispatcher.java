package org.jgame.common.net;

public class MsgDispatcher {
	private static MsgDispatcher instance = new MsgDispatcher();
	public static MsgDispatcher getInstance() {
		return instance;
	}
}
