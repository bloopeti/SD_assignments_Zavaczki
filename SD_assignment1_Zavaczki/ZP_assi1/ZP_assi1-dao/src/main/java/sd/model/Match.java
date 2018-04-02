package sd.model;

public class Match {
	private int id;
	private int level;
	private String player1;
	private String player2;
	private String tournament_name;

	public Match(int id, int level, String player1, String player2, String tournament_name) {
		super();
		this.id = id;
		this.level = level;
		this.player1 = player1;
		this.player2 = player2;
		this.tournament_name = tournament_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getTournament_name() {
		return tournament_name;
	}

	public void setTournament_name(String tournament_name) {
		this.tournament_name = tournament_name;
	}
}
