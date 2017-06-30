package org.jgame.common.net.msg;

import org.jgame.common.session.PlayerSession;

public interface MsgDelegateMethod { void invoke(PlayerSession session, Message msg); }
