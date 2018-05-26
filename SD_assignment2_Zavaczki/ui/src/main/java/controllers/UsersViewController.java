package controllers;

import Commands.user.DeleteUserCommand;
import daos.UserDao;
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
import model.User;

import java.io.IOException;

public class UsersViewController {
    @FXML
    public TableView<User> userTable;

    @FXML
    public Button getAllUButton;

    @FXML
    public Button createUButton;

    @FXML
    public Button updateUButton;

    @FXML
    public Button deleteUButton;

    @FXML
    public Button backButton;

    @FXML
    public void getAllU() {
        UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
        ObservableList<User> users = FXCollections.observableArrayList(userDao.findAll());
        System.out.println(users.toString());
        userTable.setItems(users);
    }

    @FXML
    public void createU() {
        Stage stage = (Stage) createUButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        User user = userTable.getSelectionModel().getSelectedItem();

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/userCreateView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateU() {
        Stage stage = (Stage) updateUButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        User user = userTable.getSelectionModel().getSelectedItem();

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/userUpdateView.fxml"));
            root = fxmlLoader.load();

            UserUpdateViewController userUpdateViewController = fxmlLoader.getController();
            userUpdateViewController.setUserToChange(user);

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteU() {
        User user = userTable.getSelectionModel().getSelectedItem();
        DeleteUserCommand dtc = new DeleteUserCommand(user);
        dtc.execute();

        getAllU();
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
