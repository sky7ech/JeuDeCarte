package fr.univ_lille.iut.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.univ_lille.iut.dao.PlayerPokerDao;
import fr.univ_lille.iut.data.PlayerPoker;

@Path("/playerPoker")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerPokerResources {

	private static PlayerPokerDao dao = App.dbi.open(PlayerPokerDao.class);

	public PlayerPokerResources() {
		try {
			dao.createTable();
		} catch (Exception e) {
			System.out.println("Table already exist");
		}
	}

	@POST
	public PlayerPoker createPlayerPoker(PlayerPoker playerPoker) {
		dao.insertPlayerPoker(playerPoker.getIdTable(),
				playerPoker.getPseudo(), playerPoker.getPot(),
				playerPoker.getCarte1(), playerPoker.getCarte2(),
				playerPoker.getaJoue(), playerPoker.getEstCouche(),
				playerPoker.getMise());
		return playerPoker;
	}

	@GET
	@Path("/{idTable}/{pseudo}")
	@Produces("application/json")
	public PlayerPoker getPlayer(@PathParam("idTable") int idTable,
			@PathParam("pseudo") String pseudo) {
		PlayerPoker playerPoker = dao.getPlayerPoker(idTable, pseudo);
		if (playerPoker == null) {
			throw new WebApplicationException(404);
		}
		return playerPoker;
	}
	
	@PUT
	@Path("/{mise}")
	public PlayerPoker setMise(@PathParam("mise") int mise, PlayerPoker playerPoker) {
		dao.setMise(mise,playerPoker.getPot() - mise, playerPoker.getPseudo());
		return dao.getPlayerPoker(playerPoker.getIdTable(), playerPoker.getPseudo());
	}

}
