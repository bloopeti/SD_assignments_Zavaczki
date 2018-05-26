package Commands.match;

import daos.MatchDao;
import daos.factory.DaoFactory;
import model.Match;

import java.util.ArrayList;
import java.util.List;

public class GetAllMatchCommand {
    public List<Match> execute() {
        MatchDao matchDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
        List<Match> matchs = new ArrayList<Match>();

        return matchs;
    }
}
