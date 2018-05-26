package daoTesters;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

public class TesterUser {
    public static void main(String[] args) {
        System.out.println("creating hibernate user dao");
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();

        User user = new User();
        user.setUsername("dummy1");
        user.setPass("dummy");
        user.setBalance(100);

        System.out.println("inserting user");
        userDao.insert(user);

        System.out.println("closing conn");
        //userDao.closeConnection();

        System.out.println("getting user");
        User user1 = userDao.find(1);

        System.out.println("setting user name");
        user1.setUsername("dummy1changed");

        System.out.println("updating user");
        userDao.update(user1);

        System.out.println("closing conn");
        //userDao.closeConnection();

        System.out.println("getting user");
        User user2 = userDao.find(1);

        System.out.println("deleting user");
        userDao.delete(user2);

        System.out.println("closing conn");
        userDao.closeConnection();
    }
}
