package org.jgame.gateway;

import org.jgame.common.ServerBase;

public class GatewayServer extends ServerBase {
	public static void main(String[] args) {
		try {
			GatewayServer server = new GatewayServer();
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
