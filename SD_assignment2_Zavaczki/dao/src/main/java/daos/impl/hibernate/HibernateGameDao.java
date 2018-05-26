package daos.impl.hibernate;

import daos.GameDao;
import model.Game;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class HibernateGameDao implements GameDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Game find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Game game = (Game) currentSession.get(Game.class, id);
        transaction.commit();
        return game;
    }

    public List<Game> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query q = currentSession.createQuery("from Game");
        List<Game> games = q.list();
        transaction.commit();
        return games;
    }

    public void delete(Game objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Game objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public void insert(Game objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(objectToCreate);
        transaction.commit();
    }

    public void deleteById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        //option 1
        //currentSession.createQuery("delete from game where id= :idParameter").setLong("idParameter", id).executeUpdate();

        //option 2
        Query hqlQuery = currentSession.createQuery("delete from Game where id= :idParameter");
        hqlQuery.setLong("idParameter", id);
        hqlQuery.executeUpdate();

        transaction.commit();
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
