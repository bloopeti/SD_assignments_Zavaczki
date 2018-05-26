package Commands.game;

import daos.GameDao;
import daos.factory.DaoFactory;
import model.Game;

import java.util.ArrayList;
import java.util.List;

public class GetAllGameCommand {
    public List<Game> execute() {
        GameDao gameDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
        List<Game> games = new ArrayList<Game>();

        return games;
    }
}
