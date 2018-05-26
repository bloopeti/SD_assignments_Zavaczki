package Commands.match;

import daos.MatchDao;
import daos.factory.DaoFactory;
import model.Match;

public class DeleteMatchCommand {
    Match tournament;

    public DeleteMatchCommand(Match tournament) {
        this.tournament = tournament;
    }

    public void execute() {
        MatchDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
        tournamentDao.delete(tournament);
    }
}
