package data;
/*http://commons.wikimedia.org/wiki/Playing_card
 * Pour jeu de 32 ou 52 carte
 * code image : 
 * Valeur : 1 -> 13 == AS->ROI
 * Couleur : 1 == coeur, 2 == carreau, 3 == trefle, 4 == pique
 * */
public class Carte {
	
	private int valeur;
	private int couleur;
	
	public Carte() {}
	
	
	public Carte(int valeur, int couleur) {
		super();
		this.valeur = valeur;
		this.couleur = couleur;
	}


	public int getCouleur() {
		return couleur;
	}
	public void setCouleur(int couleur) {
		this.couleur = couleur;
	}
	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
}
