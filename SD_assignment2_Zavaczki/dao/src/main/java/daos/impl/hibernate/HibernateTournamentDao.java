package daos.impl.hibernate;

import daos.TournamentDao;
import model.Tournament;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HibernateTournamentDao implements TournamentDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Tournament find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Tournament tournament = (Tournament) currentSession.get(Tournament.class, id);
        transaction.commit();
        return tournament;
    }

    public void delete(Tournament objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(Tournament objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public void insert(Tournament objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
    }

    public void deleteById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        //option 1
        //currentSession.createQuery("delete from tournament where id= :idParameter").setLong("idParameter", id).executeUpdate();

        //option 2
        Query hqlQuery = currentSession.createQuery("delete from tournament where id= :idParameter");
        hqlQuery.setLong("idParameter", id);
        hqlQuery.executeUpdate();

        transaction.commit();
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
