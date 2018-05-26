package daos;

import java.util.List;

public interface Dao<T> {
    T find(int id);

    List<T> findAll();

    void delete(T objectToDelete);

    void update(T objectToUpdate);

    void insert(T objectToCreate);

    void deleteById(long id);

    void closeConnection();
}
