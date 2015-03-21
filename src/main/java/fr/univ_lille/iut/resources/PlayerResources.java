package fr.univ_lille.iut.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.univ_lille.iut.dao.PlayerDao;
import fr.univ_lille.iut.data.Player;

@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResources {

	private static PlayerDao dao = App.dbi.open(PlayerDao.class);

	public PlayerResources() {
		try {
			dao.createTable();
		} catch (Exception e) {
			System.out.println("Table already exist");
		}
	}

	@POST
	public Player createPlayer(Player player) {
		dao.insertPlayer(player.getPseudo(), player.getMdp(), player.getNom(),
				player.getPrenom());
		return player;
	}

	@GET
	@Path("/{pseudo}")
	@Produces("application/json")
	public Player getPlayer(@PathParam("pseudo") String pseudo) {
		Player player = dao.getByPseudo(pseudo);
		if (player == null) {
			throw new WebApplicationException(404);
		}
		return player;
	}
}
