package org.jgame.hall;

import org.jgame.common.ServerBase;

public class HallServer extends ServerBase {
	public static void main(String[] args) {
		try {
			HallServer server = new HallServer();
			server.startup();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void startNetListener() {
		startNetIO(8765);
	}
}
