package org.jgame.hall;

import org.jgame.common.ServerBase;
import org.jgame.hall.processor.LoginProcessor;

public class HallServer extends ServerBase {
	public static void main(String[] args) {
		try {
			HallServer server = new HallServer();
			server.startup();
			
			System.out.println("startup success...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void startNetListener() {
		startNetIO(8765);
	}

	@Override
	protected void loadModules() {
		new LoginProcessor().register();
	}
}
