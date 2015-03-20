package fr.univ_lille.iut.dao;

import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.univ_lille.iut.data.TablePoker;

public interface TablePokerDao {

	@SqlUpdate("CREATE TABLE IF NOT EXISTS tablePoker (idTable int, pot int, flop1 TEXT,flop2 TEXT,flop3 TEXT,turn TEXT,river TEXT, CONSTRAINT pk_TablePoker PRIMARY KEY(idTable))")
	public void createTable();

	@SqlUpdate("INSERT INTO tablePoker VALUES (:idTable,:pot,:flop1,:flop2,:flop3,:turn,:river)")
	public void insertTablePoker(@Bind("idTable") int idTable,
			@Bind("pot") float pot, @Bind("flop1") String flop1,
			@Bind("flop2") String flop2, @Bind("flop3") String flop3,
			@Bind("turn") String turn, @Bind("river") String river);

	@SqlUpdate("DROP TABLE IF EXISTS tablePoker")
	public void dropTable();

	@SqlUpdate("DELETE FROM tablePoker where idTable=:idTable")
	public void deleteTablePoker(@Bind("idTable") int idTable);

	@SqlQuery("SELECT * FROM tablePoker where idTable=:idTable")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public TablePoker getTablePoker(@Bind("idTable") int idTable);

	@SqlQuery("SELECT pseudo FROM tablePoker where idTable=:idTable ORDER BY ASC")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<String> getAllPseudoFromTable(@Bind("idTable") int idTable);
}
