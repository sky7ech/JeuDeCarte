package fr.univ_lille.iut.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.univ_lille.iut.data.PlayerPoker;

public interface PlayerPokerDao {
	
	@SqlUpdate("CREATE TABLE IF NOT EXISTS playerPoker (idTable int, pot int, pseudo TEXT,carte1 TEXT,carte2 TEXT,aJoue int,estCouche int, CONSTRAINT pk_PlayerPoker PRIMARY KEY(PlayerPoker))")
	public void createTable();
	
	@SqlUpdate("INSERT INTO playerPoker VALUES (:idTable,:pot,:peuso,:carte1,:aJoue,:estCouche)")
	public void insertPlayerPoker(@Bind("idTable") int idTable, @Bind("pot") float pot, @Bind("pseudo") String pseudo, @Bind("carte1") String carte1, @Bind("carte2") String carte2, @Bind("aJoue") int aJoue, @Bind("estCouche") int estCouche);
	
	@SqlUpdate("DROP TABLE IF EXISTS PlayerPoker")
	public void dropTable();
	
	@SqlUpdate("DELETE FROM PlayerPoker where idTable=:idTable and pseudo=:pseudo")
	public void deletePlayerPoker(@Bind("idTable") int idTable,@Bind("pseudo") String pseudo);
	
	@SqlQuery("SELECT * FROM PlayerPoker where idTable=:idTable and pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public PlayerPoker getPlayerPoker(@Bind("idTable") int idTable,@Bind("pseudo") String pseudo);
	
	@SqlQuery("SELECT pot FROM PlayerPoker where pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public int getPlayerPot(@Bind("pseudo") String pseudo);
	
	@SqlQuery("SELECT isJoue FROM PlayerPoker where pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public int IsJoue(@Bind("pseudo") String pseudo);
	
	@SqlQuery("SELECT estCouche FROM PlayerPoker where pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public int estCouche(@Bind("pseudo") String pseudo);
	
	@SqlQuery("SELECT mise FROM PlayerPoker where pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public int getPlayerMise(@Bind("pseudo") String pseudo);
	

}
