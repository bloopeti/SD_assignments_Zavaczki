package daos;

import model.Tournament;

import java.util.List;

public interface TournamentDao extends Dao<Tournament>
{
    Tournament find(int id);

    List<Tournament> findAll();

    void delete(Tournament objectToDelete);

    void update(Tournament objectToUpdate);

    void insert(Tournament objectToCreate);
}