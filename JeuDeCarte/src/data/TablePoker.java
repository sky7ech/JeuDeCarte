package data;

public class TablePoker {

	private int idTable;
	private int pot;
	private String flop1;
	private String flop2;
	private String flop3;
	private String turn;
	private String river;

	public TablePoker() {
		super();
	}

	public TablePoker(int idTable, int pot, String flop1, String flop2,
			String flop3, String turn, String river) {
		super();
		this.idTable = idTable;
		this.pot = pot;
		this.flop1 = flop1;
		this.flop2 = flop2;
		this.flop3 = flop3;
		this.turn = turn;
		this.river = river;
	}

	public int getIdTable() {
		return idTable;
	}

	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}

	public String getFlop1() {
		return flop1;
	}

	public void setFlop1(String flop1) {
		this.flop1 = flop1;
	}

	public String getFlop2() {
		return flop2;
	}

	public void setFlop2(String flop2) {
		this.flop2 = flop2;
	}

	public String getFlop3() {
		return flop3;
	}

	public void setFlop3(String flop3) {
		this.flop3 = flop3;
	}

	public String getTurn() {
		return turn;
	}

	public void setTurn(String turn) {
		this.turn = turn;
	}

	public String getRiver() {
		return river;
	}

	public void setRiver(String river) {
		this.river = river;
	}

}
