package Commands.tournament;

import daos.TournamentDao;
import daos.factory.DaoFactory;
import model.Tournament;

public class AddTournamentCommand {
    private Tournament tournament;

    public AddTournamentCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    public void execute() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        tournamentDao.insert(tournament);
    }
}
