package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoiceAdminViewController {
    @FXML
    public Button tournamentsButton;

    @FXML
    public Button matchesButton;

    @FXML
    public Button gamesButton;

    @FXML
    public Button usersButton;

    @FXML
    public void goToTournaments() {
        Stage stage = (Stage) tournamentsButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
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
    public void goToMatches() {
        Stage stage = (Stage) tournamentsButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/matchesView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToGames() {
        Stage stage = (Stage) tournamentsButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/gamesView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToUsers() {
        Stage stage = (Stage) tournamentsButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/usersView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}