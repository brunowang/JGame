package org.jgame.common;

import java.util.ArrayList;
import java.util.List;

import org.jgame.common.net.NetIO;

public abstract class ServerBase {
	
	protected List<NetIO> netIoArray = new ArrayList<NetIO>();
	
	public void startup() {
		startNetListener();
	}
	
	protected void startNetIO(int port) {
		try {
			if(port==0)
				return;
			
			NetIO netio = new NetIO();
			netio.startNetListen(port);
			netIoArray.add(netio);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected abstract void startNetListener();
}
