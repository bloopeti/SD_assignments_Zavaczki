package Commands.tournament;

import daos.TournamentDao;
import daos.factory.DaoFactory;
import model.Tournament;

import java.util.ArrayList;
import java.util.List;

public class GetAllTournamentsCommand {
    public List<Tournament> execute() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        List<Tournament> tournaments = new ArrayList<Tournament>();

        return tournaments;
    }
}
