package main.java.fr.univ_lille.iut.ressources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import main.java.fr.univ_lille.iut.dao.PlayerPokerDao;
import main.java.fr.univ_lille.iut.data.PlayerPoker;

@Path("/playerPoker")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerPokerRessources {

	private static PlayerPokerDao dao = App.dbi.open(PlayerPokerDao.class);
	
	public PlayerPokerRessources() {
		try {
			dao.createTable();
		} catch (Exception e) {
			System.out.println("Table already exist");
		}
	}
	
	@POST
	public PlayerPoker createPlayerPoker(PlayerPoker playerPoker) {
		dao.insertPlayerPoker(playerPoker.getIdTable(), playerPoker.getPot(), playerPoker.getPseudo(), playerPoker.getCarte1(), playerPoker.getCarte2(), playerPoker.getaJoue(), playerPoker.getEstCouche());
		return playerPoker;
	}
	
	@GET
	@Path("/{idTable}/{pseudo}")
	public PlayerPoker getPlayer(int idTable, String pseudo) {
		PlayerPoker playerPoker = dao.getPlayerPoker(idTable, pseudo);
		if( playerPoker == null) {
			throw new WebApplicationException(404);
		}
		return playerPoker;
	}

}
