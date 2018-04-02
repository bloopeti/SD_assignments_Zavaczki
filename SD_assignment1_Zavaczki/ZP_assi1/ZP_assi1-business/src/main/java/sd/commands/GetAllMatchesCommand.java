package sd.commands;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import sd.daos.MatchDAO;
import sd.model.Match;

public class GetAllMatchesCommand {

    private List<Integer> matchIds = new ArrayList<Integer>();
    private List<Integer> matchLevels = new ArrayList<Integer>();
    
    public void GetMatchesCommand() {
    	matchIds = new ArrayList<Integer>();
    	matchLevels = new ArrayList<Integer>();
    }

    public ObservableList<Match> execute(){
    	ObservableList<Match> matches = MatchDAO.findAll();
        if (matches == null) return null;
        /*for (Match m :matches) {
        	matchIds.add(m.getId());
            matchLevels.add(m.getLevel());
        }*/
        return matches;
    }

    public List<Integer> getMatchIds() {
        return matchIds;
    }

    public List<Integer> getMatchLevels() {
        return matchLevels;
    }
}
