package org.jgame.database;

import org.jgame.common.ServerBase;

public class DatabaseServer extends ServerBase {
	public static void main(String[] args) {
		try {
			DatabaseServer server = new DatabaseServer();
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
