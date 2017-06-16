package org.jgame.common;

import org.jgame.common.net.msg.Message;

import io.netty.channel.ChannelHandlerContext;

public interface DelegateMethod { void invoke(ChannelHandlerContext ctx, Message msg); }
