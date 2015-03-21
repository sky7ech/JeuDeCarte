package fr.univ_lille.iut;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import fr.univ_lille.iut.dao.TablePokerDao;
import fr.univ_lille.iut.data.TablePoker;
import fr.univ_lille.iut.resources.App;

public class TablePokerResourcesTest extends JerseyTest {

	private static TablePokerDao dao;

	@SuppressWarnings("static-access")
	@Override
	protected Application configure() {
		App app = new App();
		DBI dbi = app.dbi;
		dao = dbi.open(TablePokerDao.class);
		return new App();
	}

	@Before
	public void init() {
		dao.dropTable();
		dao.createTable();
	}

	@Test
	public void testReadTablePokerWithId() {
		this.createTablePoker(1, 0, "flop1", "flop2", "flop3", "turn", "river");
		TablePoker tablePoker = target("/tablePoker/1").request().get(
				TablePoker.class);
		assertEquals(1, tablePoker.getIdTable());
	}

	private TablePoker createTablePoker(int idTable, int pot, String flop1,
			String flop2, String flop3, String turn, String river) {
		TablePoker tablePoker = new TablePoker(idTable, pot, flop1, flop2,
				flop3, turn, river);
		Entity<TablePoker> tablePokerEntity = Entity.entity(tablePoker,
				MediaType.APPLICATION_JSON);
		TablePoker savedTablePoker = target("/tablePoker").request()
				.post(tablePokerEntity).readEntity(TablePoker.class);
		return savedTablePoker;
	}
}
