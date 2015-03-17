package data;

public class PlayerPoker {

	private int idTable;
	private String pseudo;
	private String carte1;
	private String carte2;
	private int pot;
	private int aJoue;
	private int estCouche;

	public PlayerPoker() {
		// TODO Auto-generated constructor stub
	}

	public PlayerPoker(int idTable, String pseudo, String carte1,
			String carte2, int pot, int aJoue, int estCouche) {
		super();
		this.idTable = idTable;
		this.pseudo = pseudo;
		this.carte1 = carte1;
		this.carte2 = carte2;
		this.pot = pot;
		this.aJoue = aJoue;
		this.estCouche = estCouche;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public int getaJoue() {
		return aJoue;
	}

	public void setaJoue(int aJoue) {
		this.aJoue = aJoue;
	}

	public int getEstCouche() {
		return estCouche;
	}

	public void setEstCouche(int estCouche) {
		this.estCouche = estCouche;
	}

}
