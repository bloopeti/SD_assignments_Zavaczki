package daos.factory;

import daos.GameDao;
import daos.MatchDao;
import daos.TournamentDao;
import daos.UserDao;
import daos.factory.impl.HibernateDaoFactory;
import daos.factory.impl.JdbcDaoFactory;

public abstract class DaoFactory {

    public enum Type {
        HIBERNATE,
        JDBC;
    }

    protected DaoFactory(){

    }

    public static DaoFactory getInstance(Type factoryType) {
        switch (factoryType) {
            case HIBERNATE:
                return new HibernateDaoFactory();
            case JDBC:
                return new JdbcDaoFactory();
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

    public abstract GameDao getGameDao();

    public abstract MatchDao getMatchDao();

    public abstract TournamentDao getTournamentDao();

    public abstract UserDao getUserDao();
}
