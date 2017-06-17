package org.jgame.common.net.msg;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerContext;

public class MsgDelegate {
	private List<MsgDelegateMethod> methods;
	
	public MsgDelegate(MsgDelegateMethod method) {
		methods = new ArrayList<>();
		methods.add(method);
	}
	
	public void addMethod(MsgDelegateMethod method) {
		methods.add(method);
	}
	
	public void doIt(ChannelHandlerContext ctx, Message msg) {
		for (MsgDelegateMethod method : methods) {
			method.invoke(ctx, msg);
		}
	}
}
