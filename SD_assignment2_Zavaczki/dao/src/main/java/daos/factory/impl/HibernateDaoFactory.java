package daos.factory.impl;

import daos.GameDao;
import daos.MatchDao;
import daos.TournamentDao;
import daos.UserDao;
import daos.factory.DaoFactory;
import daos.impl.hibernate.HibernateGameDao;
import daos.impl.hibernate.HibernateTournamentDao;
import daos.impl.hibernate.HibernateUserDao;
import daos.impl.hibernate.HibernateMatchDao;

public class HibernateDaoFactory extends DaoFactory {
    @Override
    public GameDao getGameDao() {
        return new HibernateGameDao();
    }

    public MatchDao getMatchDao() {
        return new HibernateMatchDao();
    }

    public TournamentDao getTournamentDao() {
        return new HibernateTournamentDao();
    }

    @Override
    public UserDao getUserDao() {
        return new HibernateUserDao();
    }
}
