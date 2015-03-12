/*http://commons.wikimedia.org/wiki/Playing_card
 * code image : 
 * Valeur : 1 -> 13 == AS->ROI
 * Couleur : 1 == coeur, 2 == carreau, 3 == trefle, 4 == pique
 * */
public class Carte {
	
	private Object valeur;
	private Object type;
	private int id_carte;
	private int id_owner; 
		/* 0 si la carte est dans la pioche, 1 si elle est dans le flop, +x > 1 etc ... pour les cartes des joueurs */
	private int id_partie;
}
