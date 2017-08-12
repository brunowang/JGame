package org.jgame.common.module;

import org.jgame.common.net.codec.MsgDecoder;
import org.jgame.common.net.codec.MsgEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ConnectorModule {
	private static ConnectorModule instance = new ConnectorModule();

	private ConnectorModule() {}

	public static ConnectorModule getInstance() {
		return instance;
	}
	
	private EventLoopGroup group = new NioEventLoopGroup();
	
	private Bootstrap b = new Bootstrap();
	
	public ChannelFuture connect(final String ip, final int port, final ChannelInboundHandlerAdapter handler) {
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
				sc.pipeline().addLast(handler);
			}
		});
		try {
			return b.connect(ip, port).sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void shutdown() {
		group.shutdownGracefully();
	}
}
