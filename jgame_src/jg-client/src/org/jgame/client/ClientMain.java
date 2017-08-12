package org.jgame.client;

import java.util.Scanner;

import org.jgame.client.cmd.CmdExecutor;
import org.jgame.client.net.ChannelHandler;
import org.jgame.common.message.gateway2hall.login.LoginRequest;
import org.jgame.common.module.ConnectorModule;

import io.netty.channel.ChannelFuture;

public class ClientMain {

	private static ChannelFuture channelFuture;
	
	private static CmdExecutor cmdExecutor = new CmdExecutor();
	
	public static void main(String[] args) throws Exception {
		channelFuture = ConnectorModule.getInstance().connect("127.0.0.1", 8765, new ChannelHandler());
//		testSendMsg();
		
		Scanner sc= new Scanner(System.in);
		System.out.println("startup success...");
		while (true) {
			String cmd = sc.nextLine();
			if ("quit".equals(cmd)) {
				break;
			}
			cmdExecutor.executeCmd(channelFuture.channel(), cmd);
		}
		sc.close();
	}
	
	public void shutdown() {
		try {
			//等待客户端端口关闭
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void testSendMsg() {
		channelFuture.channel().writeAndFlush(new LoginRequest("bruno", "123456"));
		channelFuture.channel().writeAndFlush(new LoginRequest("bruno2", "654321"));
		channelFuture.channel().writeAndFlush(new LoginRequest("test", "123456"));
		channelFuture.channel().writeAndFlush(new LoginRequest("test2", "654321"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		channelFuture.channel().writeAndFlush(new LoginRequest("test", "123456"));
		channelFuture.channel().writeAndFlush(new LoginRequest("test2", "654321"));
//		channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("10001__bruno__123456$_".getBytes()));
//		channelFuture.channel().writeAndFlush(Unpooled.wrappedBuffer("10001__test__654321$_".getBytes()));
	}
}
