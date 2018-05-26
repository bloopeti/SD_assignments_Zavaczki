package Commands.user;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

public class CreateAccCommand {
    private String username;
    private String password;
    private String passwordRepeat;

    public CreateAccCommand(String username, String password, String passwordRepeat) {
        this.username = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }

    public int execute()
    {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        User newUser = new User();
        newUser.setIs_admin(0);
        newUser.setBalance(0);
        if(!password.equals(passwordRepeat))
            return -1; //pass not match
        else
            newUser.setPass(password);
        if(userDao.findByUsername(username) == (null))
            return -2; //username already exists
        else
            newUser.setUsername(username);

        userDao.insert(newUser);
        return 0;
    }
}
