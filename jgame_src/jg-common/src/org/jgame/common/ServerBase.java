package org.jgame.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import org.jgame.common.net.MsgDispatcher;
import org.jgame.common.net.NetIO;

import io.netty.channel.ChannelFuture;

public abstract class ServerBase {
	
	protected List<NetIO> netIoArray = new ArrayList<NetIO>();
	
	ChannelFuture channelFuture;
	
	public void startup() {
		startNetListener();
		loadModules();
	}
	
	protected void startNetIO(int port) {
		try {
			if(port==0)
				return;
			
			MsgDispatcher.getInstance().init();
			
			NetIO netio = new NetIO();
			
			Executors.newSingleThreadExecutor().submit(()->channelFuture = netio.startNetListen(port));
			
			netIoArray.add(netio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shutdown() {
		try {
			//等待服务器监听端口关闭
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void startNetListener();
	
	protected abstract void loadModules();
}
