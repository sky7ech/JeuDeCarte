package fr.univ_lille.iut.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.univ_lille.iut.data.PlayerPoker;

public interface PlayerPokerDao {

	@SqlUpdate("CREATE TABLE IF NOT EXISTS playerPoker (idTable int, pseudo TEXT, pot int, carte1 TEXT,carte2 TEXT,aJoue int,estCouche int, mise int, CONSTRAINT pk_JoueurCarte PRIMARY KEY (idTable,pseudo),CONSTRAINT fk_Table FOREIGN KEY (idTable) REFERENCES TablePoker(idTable),  CONSTRAINT fk_pseudo FOREIGN KEY (pseudo) REFERENCES Player(pseudo))")
	public void createTable();

	@SqlUpdate("INSERT INTO playerPoker VALUES (:idTable,:pseudo,:pot, :carte1, :carte2, :aJoue,:estCouche, :mise)")
	public void insertPlayerPoker(@Bind("idTable") int idTable,
			@Bind("pseudo") String pseudo, @Bind("pot") float pot,
			@Bind("carte1") String carte1, @Bind("carte2") String carte2,
			@Bind("aJoue") int aJoue, @Bind("estCouche") int estCouche, @Bind("mise") int mise);

	@SqlUpdate("DROP TABLE IF EXISTS playerPoker")
	public void dropTable();

	@SqlUpdate("DELETE FROM playerPoker where idTable=:idTable and pseudo=:pseudo")
	public void deletePlayerPoker(@Bind("idTable") int idTable,
			@Bind("pseudo") String pseudo);

	@SqlQuery("SELECT * FROM playerPoker where idTable=:idTable and pseudo=:pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public PlayerPoker getPlayerPoker(@Bind("idTable") int idTable,
			@Bind("pseudo") String pseudo);
	
	@SqlUpdate("UPDATE playerPoker SET mise=:mise, pot=:pot WHERE pseudo=:pseudo")
	public void setMise(@Bind("mise") int mise,@Bind("pot") int pot, @Bind("pseudo") String pseudo);

}
