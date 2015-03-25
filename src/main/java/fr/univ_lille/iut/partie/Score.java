package fr.univ_lille.iut.partie;

import javax.ws.rs.ForbiddenException;


/* On part du principe ici que la carte la plus forte est l'AS (14) et que les cartes précédentes  
 * valent chacune leur valeur -1 (Vallet, Dame, Roi = 11,12,13)
 */

public class Score {
	
	private static String carte1;
	private static String carte2;
	private static String carte3;
	private static String carte4;
	private static String carte5;
	private static String carte6;
	private static String carte7;
	private String []cartes = new String[8];
	private int score;
	
	public Score(String carte1, String carte2, String carte3, String carte4,
			String carte5, String carte6, String carte7) {
		this.carte1 = carte1;
		this.carte2 = carte2;
		this.carte3 = carte3;
		this.carte4 = carte4;
		this.carte5 = carte5;
		this.carte6 = carte6;
		this.carte7 = carte7;
		cartes[1]=carte1;
		cartes[2]=carte2;
		cartes[3]=carte3;
		cartes[4]=carte4;
		cartes[5]=carte5;
		cartes[6]=carte6;
		cartes[7]=carte7;
		this.score = 0;
	}
	
	public int getPaire() {
		int nb[] = new int[15];
		for (int i = 1; i < cartes.length; i++) {

			for (int tmp = 1; tmp < 15; tmp++) {
				if (cartes[i].substring(0, cartes[i].indexOf('_')).equals(tmp + ""))	
				nb[tmp]++;
			}
		}
		for (int j = 0; j < nb.length; j++) {
			if (nb[j] == 2) {
				if(j == 1) {
					score = (14 + 14);
				}
				if (j > 1) {
					score += (j -1 + 14);
				}
			}
			}
		System.out.println(score);
		return score;
	}
	
	public int getBrelan() {
		int nb[] = new int[15];
		for (int i = 1; i < cartes.length; i++) {

			for (int tmp = 1; tmp < 15; tmp++) {
				if (cartes[i].substring(0, cartes[i].indexOf('_')).equals(tmp + ""))	
				nb[tmp]++;
			}
		}
		
		for (int j = 0; j < nb.length; j++) {
			if (nb[j] == 3) {
				if(j == 1) {
					score += (14 + 14 + 14);
				}
				if (j > 1) {
					score += (j -1 + 14 + 14);
				}
			}
			}
		System.out.println(score);
		return score;
	}
	
	public int getCouleur() {
		int couleur[] = new int[4];
		String nb[] = new String[8];
		String couleur2 = "";
		for (int i = 1; i < cartes.length; i++) {

			for (int tmp = 1; tmp < 5; tmp++) {
				if (cartes[i].substring(cartes[i].indexOf('_')+1).equals(tmp + ""))
					couleur[tmp-1]++;
					if (couleur[tmp-1] == 4) {
						couleur2 = cartes[i].substring(cartes[i].indexOf('_')+1);
							}
					
			}
			for (int l = 1; l < cartes.length; l++) {
				if (couleur2.equals(cartes[l].substring(cartes[l].indexOf('_')+1))){
						nb[l]=cartes[l].substring(0, cartes[l].indexOf('_'));
					}
			}
			
		}
		for (int j = 0; j < nb.length; j++) {
			
			if(nb[j] != null && nb[j].equals("1")) {
				score += 14;
			}
			if(nb[j] != null && !nb[j].equals("1")) {
				score += Integer.parseInt(nb[j]) - 1;
			}
			System.out.println("Score : " +score);
		}
		return score;
	}
	
	public int getHigh() {
		for (int i = 1; i < cartes.length; i++) {
			if (Integer.parseInt(cartes[i].substring(0, cartes[i].indexOf('_')))>score) {
				if (Integer.parseInt(cartes[i].substring(0, cartes[i].indexOf('_'))) != 1) {
				score = Integer.parseInt(cartes[i].substring(0, cartes[i].indexOf('_')))-1;
				} else {
					score = 14;
				}
			}
		}
		System.out.println(score);
		return score;
		
	}
	
	/*public int getSuite() {
		int []tmp = new int[6];
		int []carteSuite = {0,0,0};
		
		for (int i = 1; i < cartes.length; i++) {
			for (int j = 1; j < carteSuite.length; j++) {
				tmp[j] = Integer.parseInt(cartes[j].substring(0, cartes[j].indexOf('_')));
			}
			for(int y = 1; y < cartes.length; y++) { 
				for (int j = 0; j < tmp.length; j++) {
					
					if(Integer.parseInt(cartes[i].substring(0, cartes[i].indexOf('_')))-1==tmp[j] && carteSuite[0] == 0) {
						carteSuite[0]=Integer.parseInt(cartes[y].substring(0, cartes[y].indexOf('_')))-1;
						System.out.println("Carte 1 : " + cartes[i]);
					}
					if(Integer.parseInt(cartes[i].substring(0, cartes[i].indexOf('_')))-2==tmp[j] && carteSuite[1] == 0) {
						carteSuite[1]=Integer.parseInt(cartes[y].substring(0, cartes[y].indexOf('_')))-1;
						System.out.println("Carte 2 : " + cartes[i]);
					}
					if(Integer.parseInt(cartes[i].substring(0, cartes[i].indexOf('_')))-3==tmp[j] && carteSuite[2] == 0) {
						carteSuite[2]=Integer.parseInt(cartes[y].substring(0, cartes[y].indexOf('_')))-1;
						System.out.println("Carte 3 : " + cartes[i]);
					}
				
				}
			}
			
		}
		for (int i = 0; i < carteSuite.length; i++) {
		score += carteSuite[i]; 
		System.out.println(carteSuite[i]);
		}
		System.out.println(score);
		return score;
	}*/
	
	public int getCarre() {
		int []nb = new int[15];
		for (int i = 1; i < cartes.length; i++) {
			for (int tmp = 1; tmp < 15; tmp++) {
				if (cartes[i].substring(0, cartes[i].indexOf('_')).equals(tmp + ""))	
				nb[tmp]++;
			}
			
		}
		for (int i = 0; i < nb.length; i++) {
			if (nb[i] == 4) {
				if(i == 1) {
					score += (14 + 14 + 14 + 14);
				}
				if (i > 1) {
					score += (i -1 + 14 + 14 + 14);
				}
			}
		}
		System.out.println(score);
		return score;
	}
	
	public int getFull() {
		boolean deux = false;
		boolean trois = false;
		int nb[] = new int[15];
		for (int i = 1; i < cartes.length; i++) {

			for (int tmp = 1; tmp < 15; tmp++) {
				if (cartes[i].substring(0, cartes[i].indexOf('_')).equals(tmp + ""))	
				nb[tmp]++;
			}
		}
		
		for (int i = 0; i < nb.length; i++) {
			
			if (nb[i]==2) { 
				deux = true;
			}
			
			if (nb[i]==3) { 
				trois = true;
			}
		
		}
		
		if (deux && trois) {
		
		for (int i = 0; i < nb.length; i++) {
				
			if (nb[i] == 2) {
				if(i == 1) {
					score += (14 + 14);
				}
				if (i > 1) {
					score += (i -1 + 14);
				}
			}
			
			
			if (nb[i] == 3) {
				if(i == 1) {
					score += (14 + 14 + 14);
				}
				if (i > 1) {
					score += (i -1 + 14 + 14);
				}
			}
			
			}
		}
		System.out.println(score);
		return score;
	}

	public static void main(String[] args) {
		Score s = new Score("1_1", "3_3", "3_4", "3_4", "1_4", "10_3", "11_4");
		s.getFull();

	}
	
}
	