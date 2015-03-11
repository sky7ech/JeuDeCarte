
public class Player {
	
	private int id_player;
	private String pseudo;
	//private String password;
	//private String email;
	private int mise;
	private int tresorerie;

	public Player() {}
	
	public Player(int id, String pseudo, boolean dejaJoue, int mise, int tresorerie) {
		this.id_player = id;
		this.pseudo = pseudo;
		this.mise=mise;
		this.setTresorerie(tresorerie);
		
	}

	public int getId() {
		return id_player;
	}

	public void setId(int id) {
		this.id_player = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public int getWallet() {
		return mise;
	}

	public void setWallet(int wallet) {
		this.mise = wallet;
	}

	public int getTresorerie() {
		return tresorerie;
	}

	public void setTresorerie(int tresorerie) {
		this.tresorerie = tresorerie;
	}
	
	
}

