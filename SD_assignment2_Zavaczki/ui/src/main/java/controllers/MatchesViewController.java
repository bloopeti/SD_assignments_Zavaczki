package controllers;

import Commands.match.DeleteMatchCommand;
import daos.MatchDao;
import daos.factory.DaoFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Match;

import java.io.IOException;

public class MatchesViewController {    
    @FXML
    public TableView<Match> matchesTable;

    @FXML
    public Button getAllMButton;

    @FXML
    public Button createMButton;

    @FXML
    public Button updateMButton;

    @FXML
    public Button deleteMButton;

    @FXML
    public Button backButton;

    @FXML
    public void getAllM() {
        MatchDao matchDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
        ObservableList<Match> matches = FXCollections.observableArrayList(matchDao.findAll());
        System.out.println(matches.toString());
        matchesTable.setItems(matches);
    }

    @FXML
    public void createM() {
        Stage stage = (Stage) createMButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        Match match = matchesTable.getSelectionModel().getSelectedItem();

        try {//TODO
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/emptyView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateM() {
        Stage stage = (Stage) createMButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        Match match = matchesTable.getSelectionModel().getSelectedItem();

        try {//TODO
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/emptyView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteM() {
        Match match = matchesTable.getSelectionModel().getSelectedItem();
        DeleteMatchCommand dmc = new DeleteMatchCommand(match);
        dmc.execute();

        getAllM();
    }

    @FXML
    public void goBackToChoice() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            //if(user.getIsAdmin == 1)
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/choiceAdminView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
