package daos;

import model.Tournament;

public interface TournamentDao extends Dao<Tournament>
{
    Tournament find(int id);

    void delete(Tournament objectToDelete);

    void update(Tournament objectToUpdate);

    void insert(Tournament objectToCreate);
}