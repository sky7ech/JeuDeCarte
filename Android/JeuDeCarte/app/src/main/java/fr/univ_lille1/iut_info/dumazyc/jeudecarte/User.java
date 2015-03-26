package fr.univ_lille1.iut_info.dumazyc.jeudecarte;


public class User {
    private String pseudo;
    private Integer argentDispo;
    private Integer miseActuelle;
    private Integer statut;
    private boolean cEstASonTourDeJouer;
    private Integer miseTemporaire;
    private String carte1;
    private String carte2;

    public User(String pseudo, Integer argentDispo, Integer miseActuelle, Integer statut, boolean cEstASonTourDeJouer) {
        this.pseudo = pseudo;
        this.argentDispo = argentDispo;
        this.miseActuelle = miseActuelle;
        this.statut = statut;
        this.cEstASonTourDeJouer = cEstASonTourDeJouer;
    }

    @Override
    public String toString() {
        return pseudo;
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
        if (miseActuelle != 0) {
            this.setArgentDispo(this.argentDispo - (miseActuelle - this.miseActuelle));
        }
        this.miseActuelle = miseActuelle;
    }

    public Integer getMiseTemporaire() {
        return miseTemporaire;
    }

    public void setMiseTemporaire(Integer miseTemporaire) {
        if (miseTemporaire != 0) {
            this.setArgentDispo(this.argentDispo - (miseActuelle - this.miseActuelle));
        }
        this.miseTemporaire = miseTemporaire;
    }

    public Integer getStatut() {
        return statut;
    }

    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    public boolean getcEstASonTourDeJouer() {
        return cEstASonTourDeJouer;
    }

    public void setcEstASonTourDeJouer(boolean cEstASonTourDeJouer) {
        this.cEstASonTourDeJouer = cEstASonTourDeJouer;
    }

    public String getCarte1() {
        return carte1;
    }

    public void setCarte1(String carte1) {
        this.carte1 = carte1;
    }

    public String getCarte2() {
        return carte2;
    }

    public void setCarte2(String carte2) {
        this.carte2 = carte2;
    }
}
