package test;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import ressources.App;
import dao.PlayerDao;

public class JoueurTest extends JerseyTest {
	
	private static PlayerDao dao;
	
	@Override
	protected Application configure(){
		App app = new App();
		DBI dbi = app.dbi;
		dao = dbi.open(PlayerDao.class);
		return new App();
	}
	
	@Before
	public void init() {
		dao.dropTable();
		dao.createTable();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
