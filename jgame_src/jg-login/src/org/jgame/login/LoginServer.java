package org.jgame.login;

import org.jgame.common.ServerBase;

public class LoginServer extends ServerBase {
	public static void main(String[] args) {
		try {
			LoginServer server = new LoginServer();
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
