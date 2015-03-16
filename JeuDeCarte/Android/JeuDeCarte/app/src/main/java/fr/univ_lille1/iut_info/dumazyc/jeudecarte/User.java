package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

/**
 * Created by dumazyc on 16/03/15.
 */
public class User {
    private String pseudo;
    private Integer argentDispo;
    private Integer miseActuelle;

    public User(String pseudo, Integer argentDispo, Integer miseActuelle) {
        this.pseudo = pseudo;
        this.argentDispo = argentDispo;
        this.miseActuelle = miseActuelle;
    }

    @Override
    public String toString() {
        return pseudo +" "+argentDispo +" "+miseActuelle;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Integer getArgentDispo() {
        return argentDispo;
    }

    public void setArgentDispo(Integer argentDispo) {
        this.argentDispo = argentDispo;
    }

    public Integer getMiseActuelle() {
        return miseActuelle;
    }

    public void setMiseActuelle(Integer miseActuelle) {
        this.miseActuelle = miseActuelle;
    }
}
