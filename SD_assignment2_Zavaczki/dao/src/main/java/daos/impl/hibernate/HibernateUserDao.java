package daos.impl.hibernate;

import daos.UserDao;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

public class HibernateUserDao implements UserDao {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public User find(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User user = (User) currentSession.get(User.class, id);
        transaction.commit();
        return user;
    }

    public void delete(User objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    public void update(User objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    public void insert(User objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
    }

    public void deleteById(long id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        //option 1
        //currentSession.createQuery("delete from user where id= :idParameter").setLong("idParameter", id).executeUpdate();

        //option 2
        Query hqlQuery = currentSession.createQuery("delete from user where id= :idParameter");
        hqlQuery.setLong("idParameter", id);
        hqlQuery.executeUpdate();

        transaction.commit();
    }

    public void closeConnection() {
        sessionFactory.close();
    }
}
