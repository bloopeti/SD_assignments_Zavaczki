package Commands.game;

import daos.GameDao;
import daos.factory.DaoFactory;
import model.Game;

public class AddGameCommand {
    private Game game;

    public AddGameCommand(Game game) {
        this.game = game;
    }

    public void execute() {
        GameDao gameDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
        gameDao.insert(game);
    }
}
