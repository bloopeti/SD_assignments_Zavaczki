package daos;

import model.Match;

import java.util.List;

public interface MatchDao extends Dao<Match>
{
    Match find(int id);

    List<Match> findAll();

    void delete(Match objectToDelete);

    void update(Match objectToUpdate);

    void insert(Match objectToCreate);
}