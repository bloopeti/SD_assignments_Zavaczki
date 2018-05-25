import daos.*;
import daos.factory.DaoFactory;
import model.Game;
import model.Match;
import model.Tournament;
import model.User;

import javax.persistence.GeneratedValue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testerTour {
    public static void main(String[] args) {
        System.out.println("creating hibernate tour dao");
        TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();

        Tournament tournament = new Tournament();
        tournament.setName("dummyT");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        tournament.setStart_date(dateFormat.format(date));

        System.out.println("inserting tour");
        tournamentDao.insert(tournament);

        System.out.println("closing conn");
        //tournamentDao.closeConnection();

        System.out.println("getting tour");
        Tournament tournament1 = tournamentDao.find(1);

        System.out.println("setting tour name");
        tournament1.setName("dummyTchanged");

        System.out.println("updating tour");
        tournamentDao.update(tournament1);

        System.out.println("closing conn");
        //tournamentDao.closeConnection();

        System.out.println("getting tour");
        Tournament tournament2 = tournamentDao.find(1);

        System.out.println("deleting tour");
        tournamentDao.delete(tournament2);

        System.out.println("closing conn");
        //tournamentDao.closeConnection();

        tournamentDao.closeConnection();
    }
}
