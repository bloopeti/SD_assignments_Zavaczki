import daos.GameDao;
import daos.MatchDao;
import daos.TournamentDao;
import daos.UserDao;
import daos.factory.DaoFactory;
import model.Game;
import model.Match;
import model.Tournament;
import model.User;

public class TesterGame {
    public static void main(String[] args) {
        System.out.println("creating hibernate game dao");
        GameDao gameDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();

        System.out.println("creating hibernate user dao");
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        User user3 = new User();
        user3.setUsername("x");
        user3.setPassword("X");
        User user4 = new User();
        user4.setUsername("y");
        user4.setPassword("Y");
        System.out.println("inserting user1");
        userDao.insert(user3);
        System.out.println("inserting user2");
        userDao.insert(user4);
        //userDao.closeConnection();

        System.out.println("creating hibernate tour dao");
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        Tournament tournament3 = new Tournament();
        tournament3.setStart_date("x");
        tournament3.setName("Q");
        System.out.println("inserting tour");
        tournamentDao.insert(tournament3);
        //tournamentDao.closeConnection();

        System.out.println("creating hibernate match dao");
        MatchDao matchDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
        Match match3 = new Match();
        match3.setLvl(4);
        match3.setPlayer2(user3);
        match3.setPlayer1(user4);
        match3.setTournament(tournament3);
        System.out.println("inserting match");
        matchDao.insert(match3);
        //matchDao.closeConnection();

        Game game = new Game();
        game.setMatch(match3);
        game.setPoints_p1(1);
        game.setPoints_p2(2);

        System.out.println("inserting game");
        gameDao.insert(game);

        System.out.println("closing conn");
        //gameDao.closeConnection();

        System.out.println("getting game");
        Game game1 = gameDao.find(1);

        System.out.println("setting game lvl");
        game1.setPoints_p1(20);

        System.out.println("updating game");
        gameDao.update(game1);

        System.out.println("closing conn");
        //gameDao.closeConnection();

        System.out.println("getting game");
        Game game2 = gameDao.find(1);

        System.out.println("deleting game");
        gameDao.delete(game2);

        System.out.println("closing conn");

        userDao.closeConnection();
        tournamentDao.closeConnection();
        matchDao.closeConnection();
        gameDao.closeConnection();
    }
}
