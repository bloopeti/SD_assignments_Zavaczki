package Commands.user;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

public class LoginCommand {
    private String username;
    private String password;

    public LoginCommand(String mail, String pass) {
        this.username = mail;
        this.password = pass;
    }

    public User execute()
    {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        User user = userDao.findByUsername(username);
        if (user == null) return null;
        if (!user.getUsername().equals(username)) return null; //wrong username
        if (!user.getPass().equals(password)) return null; //wrong password
        return user;
    }
}
