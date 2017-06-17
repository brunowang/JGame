package org.jgame.common.net.msg;

import org.jgame.common.net.msg.Message;

import io.netty.channel.ChannelHandlerContext;

public interface MsgDelegateMethod { void invoke(ChannelHandlerContext ctx, Message msg); }
