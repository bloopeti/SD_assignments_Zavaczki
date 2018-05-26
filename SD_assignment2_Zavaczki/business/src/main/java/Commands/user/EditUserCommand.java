package Commands.user;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

public class EditUserCommand {
    private User user;

    public EditUserCommand(User user) {
        this.user = user;
    }

    public void execute() {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        userDao.update(user);
    }
}
