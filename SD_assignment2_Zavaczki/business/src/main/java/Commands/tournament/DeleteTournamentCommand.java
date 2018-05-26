package Commands.tournament;

import daos.TournamentDao;
import daos.factory.DaoFactory;
import model.Tournament;

public class DeleteTournamentCommand {
    private Tournament tournament;

    public DeleteTournamentCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    public void execute() {
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        tournamentDao.delete(tournament);
    }
}
