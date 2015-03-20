package fr.univ_lille.iut.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.univ_lille.iut.data.Player;

public interface PlayerDao {
	
	@SqlUpdate("CREATE TABLE IF NOT EXISTS player (pseudo TEXT,mdp TEXT,nom TEXT,prenom TEXT,CONSTRAINT pk_pseudo PRIMARY KEY(pseudo))")
	public void createTable();
	
	@SqlUpdate("INSERT INTO player VALUES (:pseudo,:mdp,:nom,:prenom)")
	public void insertPlayer(@Bind("pseudo") String pseudo, @Bind("mdp") String mdp, @Bind("nom") String nom, @Bind("prenom") String prenom);

	@SqlQuery("SELECT * from player where pseudo = :pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Player getByPseudo(@Bind("pseudo") String pseudo);
	
	@SqlUpdate("DROP TABLE IF EXISTS player")
	public void dropTable();
	
	@SqlUpdate("DELETE FROM player where pseudo=:pseudo")
	public void deleteCommentaire(@Bind("pseudo") int pseudo);
}