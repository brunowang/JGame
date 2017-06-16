package org.jgame.common.net.msg;

import java.util.ArrayList;
import java.util.List;

import org.jgame.common.DelegateMethod;

import io.netty.channel.ChannelHandlerContext;

public class Delegate {
	private List<DelegateMethod> methods;
	
	public Delegate(DelegateMethod method) {
		methods = new ArrayList<>();
		methods.add(method);
	}
	
	public void addMethod(DelegateMethod method) {
		methods.add(method);
	}
	
	public void doIt(ChannelHandlerContext ctx, Message msg) {
		for (DelegateMethod method : methods) {
			method.invoke(ctx, msg);
		}
	}
}
