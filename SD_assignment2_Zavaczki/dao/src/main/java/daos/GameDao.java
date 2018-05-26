package daos;

import model.Game;

import java.util.List;

public interface GameDao extends Dao<Game>
{
    Game find(int id);

    List<Game> findAll();

    void delete(Game objectToDelete);

    void update(Game objectToUpdate);

    void insert(Game objectToCreate);
}