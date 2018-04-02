package sd.commands;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import sd.daos.TournamentDAO;
import sd.model.Tournament;

public class GetAllTournamentsCommand {
	
    private List<String> tournamentNames = new ArrayList<String>();
    private List<String> tournamentStartDates = new ArrayList<String>();
    
    public void GetTournamentsCommand() {
        tournamentNames = new ArrayList<String>();
    }

    public ObservableList<Tournament> execute(){
    	ObservableList<Tournament> tournaments = TournamentDAO.findAll();
        if (tournaments == null) return null;
        /*for (Tournament t :
                tournaments) {
            tournamentNames.add(t.getName());
            tournamentStartDates.add(t.getStart_date());
        }*/
        return tournaments;
    }

    public List<String> getTournamentNames() {
        return tournamentNames;
    }

    public List<String> getTournamentStartDates() {
        return tournamentStartDates;
    }
}
