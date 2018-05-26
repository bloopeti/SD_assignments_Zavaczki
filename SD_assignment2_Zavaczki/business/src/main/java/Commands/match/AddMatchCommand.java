package Commands.match;

import daos.MatchDao;
import daos.factory.DaoFactory;
import model.Match;

public class AddMatchCommand {
    private Match match;

    public AddMatchCommand(Match match) {
        this.match = match;
    }

    public void execute() {
        MatchDao matchDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
        matchDao.insert(match);
    }
}
