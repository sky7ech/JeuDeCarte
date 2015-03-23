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
import fr.univ_lille.iut.dao.TablePokerDao;
import fr.univ_lille.iut.data.PlayerPoker;
import fr.univ_lille.iut.data.TablePoker;

@Path("/playerPoker")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerPokerResources {

	private static PlayerPokerDao daoplayer = App.dbi.open(PlayerPokerDao.class);
	private static TablePokerDao daotable = App.dbi.open(TablePokerDao.class);

	public PlayerPokerResources() {
		try {
			daoplayer.createTable();
			daotable.createTable();
		} catch (Exception e) {
			System.out.println("Table already exist");
		}
	}

	@POST
	public PlayerPoker createPlayerPoker(PlayerPoker playerPoker) {
		daoplayer.insertPlayerPoker(playerPoker.getIdTable(),
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
		PlayerPoker playerPoker = daoplayer.getPlayerPoker(idTable, pseudo);
		if (playerPoker == null) {
			throw new WebApplicationException(404);
		}
		return playerPoker;
	}
	
	@PUT
	@Path("/{mise}")
	public PlayerPoker setMise(@PathParam("mise") int mise, PlayerPoker playerPoker) {
		daoplayer.setMise(mise,playerPoker.getPot() - mise, playerPoker.getPseudo());
		TablePoker tablePoker = daotable.getTablePoker(playerPoker.getIdTable());
		daotable.setPot(tablePoker.getPot() + mise, tablePoker.getIdTable());
		return daoplayer.getPlayerPoker(playerPoker.getIdTable(), playerPoker.getPseudo());
	}

}
