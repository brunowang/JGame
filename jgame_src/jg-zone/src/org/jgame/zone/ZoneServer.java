package org.jgame.zone;

import org.jgame.common.ServerBase;

public class ZoneServer extends ServerBase {
	public static void main(String[] args) {
		try {
			ZoneServer server = new ZoneServer();
			server.startup();
			
			System.out.println("startup success...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void startNetListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadModules() {
		// TODO Auto-generated method stub
		
	}

}
