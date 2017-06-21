package org.jgame.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import org.jgame.client.net.ChannelHandler;
import org.jgame.client.request.LoginRequest;
import org.jgame.common.net.msg.MsgPackDecoder;
import org.jgame.common.net.msg.MsgPackEncoder;

public class ClientMain {

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
				ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
				sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
				//设置字符串形式的解码
//				sc.pipeline().addLast(new StringDecoder());
				sc.pipeline().addLast(new MsgPackEncoder());
				sc.pipeline().addLast(new MsgPackDecoder());
				sc.pipeline().addLast(new ChannelHandler());
			}
		});
		
		ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();
		
		cf.channel().writeAndFlush(new LoginRequest("bruno", "123456"));
		cf.channel().writeAndFlush(new LoginRequest("bruno2", "654321"));
		cf.channel().writeAndFlush(new LoginRequest("test", "123456"));
		cf.channel().writeAndFlush(new LoginRequest("test2", "654321"));
		Thread.sleep(3000);
		cf.channel().writeAndFlush(new LoginRequest("test", "123456"));
		cf.channel().writeAndFlush(new LoginRequest("test2", "654321"));
//		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("10001__bruno__123456$_".getBytes()));
//		cf.channel().writeAndFlush(Unpooled.wrappedBuffer("10001__test__654321$_".getBytes()));
		
		//等待客户端端口关闭
		cf.channel().closeFuture().sync();
		group.shutdownGracefully();
		
	}
}
