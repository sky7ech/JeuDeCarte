package dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import data.Joueur;

public interface PlayerDao {
	
	@SqlUpdate("CREATE TABLE IF NOT EXISTS player (pseudo TEXT,mdp TEXT,nom TEXT,prenom TEXT,CONSTRAINT pk_pseudo PRIMARY KEY(pseudo))")
	public void createTable();
	
	@SqlUpdate("INSERT INTO player VALUES (:pseudo,:mdp,:nom,:prenom)")
	public void insertPlayer(@Bind("pseudo") String pseudo, @Bind("mdp") String mdp, @Bind("nom") String nom, @Bind("prenom") String prenom);

	@SqlQuery("SELECT * from player where pseudo = :pseudo")
	@RegisterMapperFactory(BeanMapperFactory.class)
	Joueur getByPseudo(@Bind("pseudo") String pseudo);
	
	/*@SqlQuery("SELECT * FROM commentaires where id_video=:id_video")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Commentaire getCommentaireByVideo(@Bind("id_video") int id_video);
	
	@SqlQuery("SELECT * FROM commentaires where id_utilisateur=:id_utilisateur")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Commentaire getCommentaireByUser(@Bind("id_utilisateur") int id_utilisateur);
	
	@SqlQuery("SELECT * FROM commentaires")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Commentaire> getAllCommentaire();
	
	@SqlQuery("SELECT * FROM commentaires ORDER BY date DESC, heure DESC")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Commentaire> getAllCommentaireDesc();
	
	@SqlQuery("SELECT * FROM commentaires where id_video=:id_video ORDER BY date DESC, heure DESC LIMIT :limit")
	@RegisterMapperFactory(BeanMapperFactory.class)
	public Iterator<Commentaire> getAllCommentaireDescLimit(@Bind("id_video") int id_video, @Bind("limit") int limit);*/
	
	@SqlUpdate("DROP TABLE IF EXISTS player")
	public void dropTable();
	
	@SqlUpdate("DELETE FROM player where pseudo=:pseudo")
	public void deleteCommentaire(@Bind("pseudo") int pseudo);
}