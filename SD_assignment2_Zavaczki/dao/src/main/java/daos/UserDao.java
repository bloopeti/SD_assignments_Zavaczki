package daos;

import model.User;

import java.util.List;

public interface UserDao extends Dao<User>
{
    User find(int id);

    List<User> findAll();

    void delete(User objectToDelete);

    void update(User objectToUpdate);

    void insert(User objectToCreate);

    User findByUsername(String username);
}