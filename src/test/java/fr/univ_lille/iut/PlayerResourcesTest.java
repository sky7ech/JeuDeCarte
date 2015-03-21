package fr.univ_lille.iut;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import fr.univ_lille.iut.dao.PlayerDao;
import fr.univ_lille.iut.data.Player;
import fr.univ_lille.iut.resources.App;

public class PlayerResourcesTest extends JerseyTest {

	private static PlayerDao dao;
	
	@SuppressWarnings("static-access")
	@Override
	protected Application configure() {
		App app = new App();
		DBI dbi = app.dbi;
		dao = dbi.open(PlayerDao.class);
        return new App();
	}
	
	@Before
	public void init () {
		dao.dropTable();
		dao.createTable();			
	}
	
	@Test
	public void testReadPlayerWithNameTest() {
		this.createPlayer("test", "lol", "test", "test");
		Player player = target("/player/test").request().get(Player.class);
		assertEquals("test",player.getPseudo());
	}
	
	private Player createPlayer(String pseudo, String mdp, String nom, String prenom) {
		Player player = new Player(pseudo, mdp, nom, prenom);
		Entity<Player> playerEntity = Entity.entity(player, MediaType.APPLICATION_JSON);
		Player savedPlayer = target("/player").request().post(playerEntity).readEntity(Player.class);
		return savedPlayer;
	}
}
