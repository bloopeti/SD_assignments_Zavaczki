package controllers;

import Commands.game.DeleteGameCommand;
import daos.GameDao;
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
import model.Game;

import java.io.IOException;

public class GamesViewController {
    @FXML
    public TableView<Game> gamesTable;

    @FXML
    public Button getAllGButton;

    @FXML
    public Button createGButton;

    @FXML
    public Button updateGButton;

    @FXML
    public Button deleteGButton;

    @FXML
    public Button backButton;

    @FXML
    public void getAllG() {
        GameDao gameDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
        ObservableList<Game> games = FXCollections.observableArrayList(gameDao.findAll());
        System.out.println(games.toString());
        gamesTable.setItems(games);
    }

    @FXML
    public void createG() {
        Stage stage = (Stage) createGButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        Game game = gamesTable.getSelectionModel().getSelectedItem();

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
    public void updateG() {
        Stage stage = (Stage) createGButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        Game game = gamesTable.getSelectionModel().getSelectedItem();

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
    public void deleteG() {
        Game game = gamesTable.getSelectionModel().getSelectedItem();
        DeleteGameCommand dgc = new DeleteGameCommand(game);
        dgc.execute();

        getAllG();
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