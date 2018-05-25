package daos;

import model.User;

public interface UserDao extends Dao<User>
{
    User find(int id);

    void delete(User objectToDelete);

    void update(User objectToUpdate);

    void insert(User objectToCreate);
}