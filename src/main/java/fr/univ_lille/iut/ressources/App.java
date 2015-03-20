package fr.univ_lille.iut.ressources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

@ApplicationPath("/")
public class App extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(PlayerRessources.class);
		s.add(TablePokerRessources.class);
		s.add(PlayerPokerRessources.class);
		return s;
	}

	public static DBI dbi;
	static {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:poker.db");
		dbi = new DBI(ds);
	}
}