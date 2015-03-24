package fr.univ_lille.iut.partie;

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
	}
	
	public int getPaire() {
		int nb[] = new int[15];
		for (int i = 1; i < 8; i++) {

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
		for (int i = 1; i < 8; i++) {

			for (int tmp = 1; tmp < 15; tmp++) {
				if (cartes[i].substring(0, cartes[i].indexOf('_')).equals(tmp + ""))	
				nb[tmp]++;
			}
		}
		
		for (int j = 0; j < nb.length; j++) {
			if (nb[j] == 3) {
				if(j == 1) {
					score = (14 + 14 + 14);
				}
				if (j > 1) {
					score += (j -1 + 14 + 14);
				}
			}
			}
		System.out.println(score);
		return score;
	}

	public static void main(String[] args) {
		Score s = new Score("1_", "3_", "3_", "10_", "1_", "4_", "3_");
		s.getBrelan();

	}
	
}
	