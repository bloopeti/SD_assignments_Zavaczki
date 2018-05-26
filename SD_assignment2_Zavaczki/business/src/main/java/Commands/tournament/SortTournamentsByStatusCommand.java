package Commands.tournament;

import model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class SortTournamentsByStatusCommand {
    private List<Tournament> tournaments = new ArrayList<Tournament>();
    private String status;

    public SortTournamentsByStatusCommand(List<Tournament> tournaments, String status) {
        this.tournaments = tournaments;
        this.status = status;
    }

    public List<Tournament> execute() {
        List<Tournament> tournamentsSorted = new ArrayList<Tournament>();

        for (Tournament t : tournaments)
        {
            CalculateTournamentStatusCommand ctsc = new CalculateTournamentStatusCommand(t);
            if (ctsc.execute().equals(status))
                tournamentsSorted.add(t);
        }

        return tournamentsSorted;
    }
}
