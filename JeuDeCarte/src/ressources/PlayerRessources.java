package ressources;

import dao.PlayerDao;

public class PlayerRessources {
	
	private static PlayerDao dao = null;

	public PlayerRessources() {
		try {
			dao.createTable();
		} catch(Exception e) {
			System.out.println("Table already exist");
		}
	}

}
