package daoTesters;

import daos.MatchDao;
import daos.TournamentDao;
import daos.UserDao;
import daos.factory.DaoFactory;
import model.Match;
import model.Tournament;
import model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TesterMatch {
    public static void main(String[] args) {

        System.out.println("creating hibernate match dao");
        MatchDao matchDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();

        System.out.println("creating hibernate user dao");
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        User user3 = new User();
        user3.setUsername("x");
        user3.setPass("X");
        user3.setBalance(100);
        User user4 = new User();
        user4.setUsername("y");
        user4.setPass("Y");
        user4.setBalance(100);
        System.out.println("inserting user1");
        userDao.insert(user3);
        System.out.println("inserting user2");
        userDao.insert(user4);
        //userDao.closeConnection();

        System.out.println("creating hibernate tour dao");
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
        Tournament tournament3 = new Tournament();
        tournament3.setName("dummyTunpaid");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        tournament3.setStart_date(dateFormat.format(date));
        tournament3.setFee(0);
        tournament3.setTotal_pot(0);
        System.out.println("inserting tour");
        tournamentDao.insert(tournament3);
        //tournamentDao.closeConnection();

        Match match = new Match();
        match.setPlayer1(user3);
        match.setPlayer2(user4);
        match.setLvl(1);
        match.setTournament(tournament3);

        System.out.println("inserting match");
        matchDao.insert(match);

        System.out.println("closing conn");
        //matchDao.closeConnection();

        System.out.println("getting match");
        Match match1 = matchDao.find(1);

        System.out.println("setting match lvl");
        match1.setLvl(2);

        System.out.println("updating match");
        matchDao.update(match1);

        System.out.println("closing conn");
        // matchDao.closeConnection();

        System.out.println("getting match");
        Match match2 = matchDao.find(1);

        System.out.println("deleting match");
        matchDao.delete(match2);

        System.out.println("closing conn");
        //userDao.closeConnection();

        userDao.closeConnection();
        tournamentDao.closeConnection();
        matchDao.closeConnection();
    }
}
