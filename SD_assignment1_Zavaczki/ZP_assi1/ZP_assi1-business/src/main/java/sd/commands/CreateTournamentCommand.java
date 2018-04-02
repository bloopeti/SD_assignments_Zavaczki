package sd.commands;

import sd.daos.TournamentDAO;
import sd.model.Tournament;

public class CreateTournamentCommand {
	
	private Tournament tournament;
    
    public CreateTournamentCommand(String name, String start_date) {
    	this.tournament = new Tournament(name, start_date);
    }

    public void execute(){
    	TournamentDAO.insert(tournament);
    }
}
