package Commands.tournament;

import daos.TournamentDao;
import daos.factory.DaoFactory;
import model.Tournament;

public class EditTournamentCommand {
    private Tournament tournament;

    public EditTournamentCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    public void execute() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        tournamentDao.update(tournament);
    }
}
