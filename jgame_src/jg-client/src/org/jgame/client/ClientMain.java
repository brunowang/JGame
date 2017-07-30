package org.jgame.client;

import java.util.Scanner;

import org.jgame.client.cmd.CmdExecutor;
import org.jgame.client.net.ChannelHandler;
import org.jgame.common.message.gateway2hall.login.LoginRequest;
import org.jgame.common.net.codec.MsgDecoder;
import org.jgame.common.net.codec.MsgEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientMain {

	private static Channel channel;
	
	private static CmdExecutor cmdExecutor = new CmdExecutor();
	
	public static void main(String[] args) throws Exception {
		
		EventLoopGroup group = new NioEventLoopGroup();
		
		Bootstrap b = new Bootstrap();
		b.group(group)
		 .channel(NioSocketChannel.class)
		 .option(ChannelOption.SO_SNDBUF, 32*1024)
		 .option(ChannelOption.SO_RCVBUF, 32*1024)
		 .handler(new ChannelInitializer<SocketChannel>() {
			@Override
			protected void initChannel(SocketChannel sc) throws Exception {
				//设置特殊分隔符
//				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				//设置字符串形式的解码
//				sc.pipeline().addLast(new StringDecoder());
				sc.pipeline().addLast(new MsgEncoder());
				sc.pipeline().addLast(new MsgDecoder());
				sc.pipeline().addLast(new ChannelHandler());
			}
		});
		
		ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();
		channel = cf.channel();
		
//		testSendMsg();
		Scanner sc= new Scanner(System.in);
		while (true) {
			String cmd = sc.nextLine();
			if ("quit".equals(cmd)) {
				break;
			}
			cmdExecutor.executeCmd(channel, cmd);
		}
		sc.close();
		
		//等待客户端端口关闭
		channel.closeFuture().sync();
		group.shutdownGracefully();
		
	}
	
	@SuppressWarnings("unused")
	private static void testSendMsg() {
		channel.writeAndFlush(new LoginRequest("bruno", "123456"));
		channel.writeAndFlush(new LoginRequest("bruno2", "654321"));
		channel.writeAndFlush(new LoginRequest("test", "123456"));
		channel.writeAndFlush(new LoginRequest("test2", "654321"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		channel.writeAndFlush(new LoginRequest("test", "123456"));
		channel.writeAndFlush(new LoginRequest("test2", "654321"));
//		channel.writeAndFlush(Unpooled.wrappedBuffer("10001__bruno__123456$_".getBytes()));
//		channel.writeAndFlush(Unpooled.wrappedBuffer("10001__test__654321$_".getBytes()));
	}
}
