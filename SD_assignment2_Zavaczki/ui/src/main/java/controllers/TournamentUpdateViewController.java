package controllers;

import daos.TournamentDao;
import daos.factory.DaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Tournament;

import java.io.IOException;

public class TournamentUpdateViewController {
    @FXML
    public Label nameLabel;

    @FXML
    public TextField nameField;

    @FXML
    public Label start_dateLabel;

    @FXML
    public TextField start_dateField;

    @FXML
    public Label feeLabel;

    @FXML
    public TextField feeField;

    @FXML
    public Label total_potLabel;

    @FXML
    public TextField total_potField;

    @FXML
    public Button saveButton;

    @FXML
    public Button backButton;

    private Tournament tournamentToChange;

    @FXML
    public void saveObject() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            TournamentDao tournamentDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
            Tournament tournament = new Tournament();
            tournament.setId(tournamentToChange.getId());
            tournament.setName(tournamentToChange.getName());
            tournament.setStart_date(tournamentToChange.getStart_date());
            tournament.setFee(tournamentToChange.getFee());
            tournament.setTotal_pot(tournamentToChange.getTotal_pot());

            if (nameField.getText() != null && !nameField.getText().trim().equals(""))
                tournament.setName(nameField.getText());
            if (start_dateField.getText() != null && !start_dateField.getText().trim().equals(""))
                tournament.setStart_date(start_dateField.getText());
            if (feeField.getText() != null && !feeField.getText().trim().equals(""))
                tournament.setFee(Integer.parseInt(feeField.getText()));
            if (total_potField.getText() != null && !total_potField.getText().trim().equals(""))
                tournament.setTotal_pot(Integer.parseInt(total_potField.getText()));

            tournamentDao.update(tournament);

            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/tournamentsView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBackToTableView() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            //if(tournament.getIsAdmin == 1)
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/tournamentsView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTournamentToChange(Tournament tournamentToChange) {
        this.tournamentToChange = tournamentToChange;
        this.nameField.setText(tournamentToChange.getName());
        this.start_dateField.setText(tournamentToChange.getStart_date());
        this.feeField.setText(String.valueOf(tournamentToChange.getFee()));
        this.total_potField.setText(String.valueOf(tournamentToChange.getTotal_pot()));
    }
}
