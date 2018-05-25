package daos.factory.impl;

import daos.GameDao;
import daos.MatchDao;
import daos.TournamentDao;
import daos.UserDao;
import daos.factory.DaoFactory;
import daos.impl.jdbc.JdbcGameDao;
import daos.impl.jdbc.JdbcMatchDao;
import daos.impl.jdbc.JdbcTournamentDao;
import daos.impl.jdbc.JdbcUserDao;

public class JdbcDaoFactory extends DaoFactory {
    public GameDao getGameDao() {
        return new JdbcGameDao();
    }

    public MatchDao getMatchDao() {
        return new JdbcMatchDao();
    }

    public TournamentDao getTournamentDao() {
        return new JdbcTournamentDao();
    }

    public UserDao getUserDao() {
        return new JdbcUserDao();
    }
}
