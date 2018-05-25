package daos;

import model.Match;

public interface MatchDao extends Dao<Match>
{
    Match find(int id);

    void delete(Match objectToDelete);

    void update(Match objectToUpdate);

    void insert(Match objectToCreate);
}