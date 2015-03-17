package ressources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import dao.PlayerDao;
import data.Player;

@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerRessources {

	private static PlayerDao dao = null;

	public PlayerRessources() {
		try {
			dao.createTable();
		} catch (Exception e) {
			System.out.println("Table already exist");
		}
	}
	
	@POST
	public Player createPlayer(Player player) {
		dao.insertPlayer(player.getPseudo(), player.getMdp(), player.getNom(), player.getPrenom());
		return player;
	}
	
	@GET
	@Path("/{pseudo}")
	public Player getPlayer(String pseudo) {
		Player player = dao.getByPseudo(pseudo);
		if( player == null) {
			throw new WebApplicationException(404);
		}
		return player;
	}
}