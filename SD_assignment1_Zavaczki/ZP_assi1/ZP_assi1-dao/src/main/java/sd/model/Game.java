package sd.model;

public class Game {
	private int id;
	private int points_p1;
	private int points_p2;
	private int match_id;

	public Game(int id, int points_p1, int points_p2, int match_id) {
		super();
		this.id = id;
		this.points_p1 = points_p1;
		this.points_p2 = points_p2;
		this.match_id = match_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoints_p1() {
		return points_p1;
	}

	public void setPoints_p1(int points_p1) {
		this.points_p1 = points_p1;
	}

	public int getPoints_p2() {
		return points_p2;
	}

	public void setPoints_p2(int points_p2) {
		this.points_p2 = points_p2;
	}

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}
}
