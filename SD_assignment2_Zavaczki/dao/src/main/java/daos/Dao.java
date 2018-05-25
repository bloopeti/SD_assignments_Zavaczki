package daos;

public interface Dao<T> {
    T find(int id);

    void delete(T objectToDelete);

    void update(T objectToUpdate);

    void insert(T objectToCreate);

    void deleteById(long id);

    void closeConnection();
}
