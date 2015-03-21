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
}
