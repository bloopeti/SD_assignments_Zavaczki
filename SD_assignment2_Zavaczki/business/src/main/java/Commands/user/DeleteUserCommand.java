package Commands.user;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

public class DeleteUserCommand {
    private User user;

    public DeleteUserCommand(User user) {
        this.user = user;
    }

    public void execute() {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        userDao.delete(user);
    }
}
