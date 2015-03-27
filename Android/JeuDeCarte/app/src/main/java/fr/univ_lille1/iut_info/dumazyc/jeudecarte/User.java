package fr.univ_lille1.iut_info.dumazyc.jeudecarte;

/**
 * Classe qui symbolise l'utilisateur de notre application
 */
public class User {
    private String pseudo;
    private Integer argentDispo;
    private Integer miseActuelle = 0;
    private Integer statut;
    private boolean cEstASonTourDeJouer;
    private Integer miseTemporaire = 0;
    private String carte1;
    private String carte2;

    /**
     * Constructeur de la classe User
     *
     * @param pseudo              pseudo du joueur
     * @param argentDispo         argent disponible du joueur
     * @param statut              statut du joueur :
     *                            1 -> petite blind
     *                            2 -> grosse blind
     *                            3 -> s'est couch&eacute;
     *                            4 -> a suivi
     *                            5 -> a relanc&eacute;
     * @param cEstASonTourDeJouer true si c'est &agrave; son tour de jouer, dalse sinon
     */
    public User(String pseudo, Integer argentDispo, Integer statut, boolean cEstASonTourDeJouer) {
        this.pseudo = pseudo;
        this.argentDispo = argentDispo;

        this.statut = statut;
        this.cEstASonTourDeJouer = cEstASonTourDeJouer;
    }

    @Override
    public String toString() {
        return getPseudo();
    }

    /**
     * Permet de r&eacute;cup&eacute;rer le pseudo du joueur
     *
     * @return le pseudo du joueur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Permet de r&eacute;cup&eacute;rer l'argent du joueur
     *
     * @return l'argent du joueur
     */
    public Integer getArgentDispo() {
        return argentDispo;
    }

    /**
     * Permet de modifier l'argent du joueur
     *
     * @param argentDispo l'argent du joueur
     */
    public void setArgentDispo(Integer argentDispo) {
        this.argentDispo = argentDispo;
    }

    /**
     * Permet de r&eacute;cup&eacute;rer la mise actuelle du joueur
     *
     * @return la mise actuelle du joueur
     */
    public Integer getMiseActuelle() {
        return miseActuelle;
    }

    /**
     * Permet de modifier la mise actuelle du joueur
     *
     * @param miseActuelle la mise actuelle du joueur
     */
    public void setMiseActuelle(Integer miseActuelle) {
        if (miseActuelle != 0) {
            this.setArgentDispo(this.argentDispo - (miseActuelle - this.miseActuelle));
        }
        this.miseActuelle = miseActuelle;
    }

    /**
     * Permet de r&eacute;cup&eacute;rer la mise temporaire du joueur (utile pour la relance)
     *
     * @return la mise temporaire du joueur
     */
    public Integer getMiseTemporaire() {
        return miseTemporaire;
    }

    /**
     * Permet de modifier la mise temporaire du joueur (utile pour la relance)
     *
     * @param miseTemporaire la mise temporaire du joueur
     */
    public void setMiseTemporaire(Integer miseTemporaire) {
        if (miseTemporaire != 0) {
            this.setArgentDispo(this.argentDispo - (miseActuelle - this.miseActuelle));
        }
        this.miseTemporaire = miseTemporaire;
    }

    /**
     * Permet de r&eacute;cup&eacute;rer le statut du joueur
     *
     * @return le statut du joueur
     */
    public Integer getStatut() {
        return statut;
    }

    /**
     * Permet de modifier le statut du joueur
     *
     * @param statut le statut du joueur
     */
    public void setStatut(Integer statut) {
        this.statut = statut;
    }

    /**
     * Permet de savoir si c'est &agrave; l'utilisateur de jouer
     *
     * @return true si c'est &agrave; son tour, false sinon
     */
    public boolean getcEstASonTourDeJouer() {
        return cEstASonTourDeJouer;
    }

    /**
     * Permet de modifier si c'est &agrave; l'utilisateur de jouer
     *
     * @param cEstASonTourDeJouer true si c'est &agrave; son tour, false sinon
     */
    public void setcEstASonTourDeJouer(boolean cEstASonTourDeJouer) {
        this.cEstASonTourDeJouer = cEstASonTourDeJouer;
    }

    /**
     * Permet de r&eacute;cup&eacute;rer la premi&egrave;re carte de l'utilisateur
     *
     * @return la premi&egrave;re carte de l'utilisateur
     */
    public String getCarte1() {
        return carte1;
    }

    /**
     * Permet de modifier la premi&egrave;re carte de l'utilisateur
     *
     * @param carte1 la premi&egrave;re carte de l'utilisateur
     */
    public void setCarte1(String carte1) {
        this.carte1 = carte1;
    }

    /**
     * Permet de r&eacute;cup&eacute;rer la deuxi&egrave;me carte de l'utilisateur
     *
     * @return la deuxi&egrave;me carte de l'utilisateur
     */
    public String getCarte2() {
        return carte2;
    }

    /**
     * Permet de modifier la deuxi&egrave;me carte de l'utilisateur
     *
     * @param carte2 la deuxi&egrave;me carte de l'utilisateur
     */
    public void setCarte2(String carte2) {
        this.carte2 = carte2;
    }
}
