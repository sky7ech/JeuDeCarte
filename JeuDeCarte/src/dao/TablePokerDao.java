package dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface TablePokerDao {

	@SqlUpdate("CREATE TABLE IF NOT EXISTS tablePoker (idTable int, pot int, flop1 TEXT,flop2 TEXT,flop3 TEXT,turn TEXT,river TEXT, CONSTRAINT pk_TablePoker PRIMARY KEY(TablePoker))")
	public void createTable();
	
	@SqlUpdate("INSERT INTO tablePoker VALUES (:idTable,:pot,:flop1,:flop2,:flop3,:turn,:river)")
	public void insertTablePoker(@Bind("idTable") int idTable, @Bind("pot") float pot, @Bind("flop1") String flop1, @Bind("flop2") String flop2, @Bind("flop3") String flop3, @Bind("turn") String turn, @Bind("river") String river);
	
	@SqlUpdate("DROP TABLE IF EXISTS tablePoker")
	public void dropTable();
	
	@SqlUpdate("DELETE FROM tablePoker where idTable=:idTable")
	public void deleteTablePoker(@Bind("idTable") int idTable);
}
