package Commands.user;

import daos.UserDao;
import daos.factory.DaoFactory;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class GetAllUsersCommand {
    public List<User> execute() {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        List<User> users = new ArrayList<User>();

        return users;
    }
}
