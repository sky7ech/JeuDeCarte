package fr.univ_lille.iut.resources;

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
		s.add(PlayerResources.class);
		s.add(TablePokerResources.class);
		s.add(PlayerPokerResources.class);
		return s;
	}

	public static DBI dbi;
	static {
		SQLiteDataSource ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:poker.db");
		dbi = new DBI(ds);
	}
}