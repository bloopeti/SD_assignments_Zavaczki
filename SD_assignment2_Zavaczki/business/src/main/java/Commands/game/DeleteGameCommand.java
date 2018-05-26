package Commands.game;

import daos.GameDao;
import daos.factory.DaoFactory;
import model.Game;

public class DeleteGameCommand {
    private Game game;

    public DeleteGameCommand(Game game) {
        this.game = game;
    }

    public void execute() {
        GameDao gameDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
        gameDao.delete(game);
    }
}
