package daos.impl.hibernate;

import daos.MatchDao;
import model.Match;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HibernateMatchDao implements MatchDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Match find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Match match = (Match) currentSession.get(Match.class, id);
        transaction.commit();
        return match;
    }

    public void delete(Match objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Match objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public void insert(Match objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.save(objectToCreate);
        transaction.commit();
    }

    public void deleteById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        //option 1
        //currentSession.createQuery("delete from match where id= :idParameter").setLong("idParameter", id).executeUpdate();

        //option 2
        Query hqlQuery = currentSession.createQuery("delete from match where id= :idParameter");
        hqlQuery.setLong("idParameter", id);
        hqlQuery.executeUpdate();

        transaction.commit();
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
