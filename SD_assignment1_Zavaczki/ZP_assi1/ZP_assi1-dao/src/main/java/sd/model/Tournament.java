package sd.model;

public class Tournament {
	private String name;
	private String start_date;

	public Tournament(String name, String start_date) {
		super();
		this.name = name;
		this.start_date = start_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
}
