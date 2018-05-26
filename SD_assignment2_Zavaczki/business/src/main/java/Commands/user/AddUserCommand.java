package Commands.user;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

public class AddUserCommand {
    private User user;

    public AddUserCommand(User user) {
        this.user = user;
    }

    public void execute() {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        userDao.insert(user);
    }
}
