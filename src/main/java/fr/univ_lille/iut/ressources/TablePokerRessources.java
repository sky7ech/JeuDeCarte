package fr.univ_lille.iut.ressources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import fr.univ_lille.iut.dao.TablePokerDao;
import fr.univ_lille.iut.data.TablePoker;

@Path("/tablePoker")
@Produces(MediaType.APPLICATION_JSON)
public class TablePokerRessources {

	private static TablePokerDao dao = App.dbi.open(TablePokerDao.class);

	public TablePokerRessources() {
		try {
			dao.createTable();
		} catch (Exception e) {
			System.out.println("Table already exist !");
		}
	}

	@POST
	public TablePoker createTablePoker(TablePoker tablePoker) {
		dao.insertTablePoker(tablePoker.getIdTable(), tablePoker.getPot(),
				tablePoker.getFlop1(), tablePoker.getFlop2(),
				tablePoker.getFlop3(), tablePoker.getTurn(),
				tablePoker.getRiver());
		return tablePoker;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public TablePoker getTablePoker(@PathParam("idTable") int idTable) {
		TablePoker tablePoker = dao.getTablePoker(idTable);
		if (tablePoker == null) {
			throw new WebApplicationException(404);
		}
		return tablePoker;
	}

}
