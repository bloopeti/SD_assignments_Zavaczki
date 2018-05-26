package Commands.match;

import daos.MatchDao;
import daos.factory.DaoFactory;
import model.Match;

public class EditMatchCommand {
    private Match match;

    public EditMatchCommand(Match match) {
        this.match = match;
    }

    public void execute() {
        MatchDao matchDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
        matchDao.update(match);
    }
}
