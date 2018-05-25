package daos.impl.hibernate;

import daos.UserDao;
import javafx.scene.control.Alert;
import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

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

    public User findByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query q = currentSession.createQuery("from User where username = :stat").setParameter("stat", username);
        List<User> list = q.list();
        transaction.commit();
        if(list.size()==0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password!");
            alert.showAndWait();
            return null;
        }
        User u = list.get(0);
        return u;
    }
}
