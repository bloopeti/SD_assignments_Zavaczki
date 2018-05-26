package Commands.tournament;

import model.Tournament;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateTournamentStatusCommand {
    Tournament tournament;

    public CalculateTournamentStatusCommand(Tournament tournament) {
        this.tournament = tournament;
    }

    public String execute() {
        try {
            Date todayDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String tournamentDateS = tournament.getStart_date();
            Date tournamentDate = dateFormat.parse(tournamentDateS);

            if ((dateFormat.format(todayDate)).equals(tournamentDateS))
                return "ongoing";
            else if (todayDate.compareTo(tournamentDate) > 0)
                return "finished";
            else if (todayDate.compareTo(tournamentDate) < 0)
                return "upcoming";
            else
                return "somethingsFucky";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "somethingsFucky2";
    }
}
