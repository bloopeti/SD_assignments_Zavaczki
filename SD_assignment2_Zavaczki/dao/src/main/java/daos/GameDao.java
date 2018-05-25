package daos;

import model.Game;

public interface GameDao extends Dao<Game>
{
    Game find(int id);

    void delete(Game objectToDelete);

    void update(Game objectToUpdate);

    void insert(Game objectToCreate);
}