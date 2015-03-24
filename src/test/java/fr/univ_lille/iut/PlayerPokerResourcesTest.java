package fr.univ_lille.iut;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import fr.univ_lille.iut.dao.PlayerPokerDao;
import fr.univ_lille.iut.data.PlayerPoker;
import fr.univ_lille.iut.data.TablePoker;
import fr.univ_lille.iut.resources.App;

public class PlayerPokerResourcesTest extends JerseyTest {

	private static PlayerPokerDao dao;

	@SuppressWarnings("static-access")
	@Override
	protected Application configure() {
		App app = new App();
		DBI dbi = app.dbi;
		dao = dbi.open(PlayerPokerDao.class);
		return new App();
	}

	@Before
	public void init() {
		dao.dropTable();
		dao.createTable();
	}

	@Test
	public void testReadPlayerPokerWithNameAndId() {
		this.init();
		this.createPlayerPoker(1, "test", 0, "carte1", "carte2", 0, 0, 0);
		PlayerPoker playerPoker = target("/playerPoker/1/test").request().get(
				PlayerPoker.class);
		assertEquals("test", playerPoker.getPseudo());
		assertEquals(1, playerPoker.getIdTable());
	}

	private PlayerPoker createPlayerPoker(int idTable, String pseudo, int pot,
			String carte1, String carte2, int aJoue, int estCouche, int mise) {
		PlayerPoker playerPoker = new PlayerPoker(idTable, pseudo, pot, carte1,
				carte2, aJoue, estCouche, mise);
		Entity<PlayerPoker> playerPokerEntity = Entity.entity(playerPoker,
				MediaType.APPLICATION_JSON);
		PlayerPoker savedPlayerPoker = target("/playerPoker").request()
				.post(playerPokerEntity).readEntity(PlayerPoker.class);
		return savedPlayerPoker;
	}
	
	@Test
	public void testUpdateMise() {
		this.init();
		PlayerPoker playerPoker = this.createPlayerPoker(1, "test", 1200, "carte1", "carte2", 0, 0, 0);
		Entity<PlayerPoker> playerPokerEntity = Entity.entity(playerPoker,
				MediaType.APPLICATION_JSON);
		int statut = target("/playerPoker/500").request().put(playerPokerEntity).getStatus();
		assertEquals(200,statut);
		PlayerPoker MajplayerPoker = target("/playerPoker/1/test").request().get(
				PlayerPoker.class);
		TablePoker tablePoker = target("/tablePoker/1").request().get(TablePoker.class);
		assertEquals(500, tablePoker.getPot());
		assertEquals(500,MajplayerPoker.getMise());
		assertEquals(700, MajplayerPoker.getPot());
	}
}
