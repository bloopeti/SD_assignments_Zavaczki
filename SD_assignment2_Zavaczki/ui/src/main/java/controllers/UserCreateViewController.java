package controllers;

import daos.UserDao;
import daos.factory.DaoFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class UserCreateViewController {
    @FXML
    public Label usernameLabel;

    @FXML
    public TextField usernameField;

    @FXML
    public Label passLabel;

    @FXML
    public TextField passField;

    @FXML
    public Label is_adminLabel;

    @FXML
    public TextField is_adminField;

    @FXML
    public Label balanceLabel;

    @FXML
    public TextField balanceField;

    @FXML
    public Button saveButton;

    @FXML
    public Button backButton;

    @FXML
    public void saveObject() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            UserDao userDao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
            User user = new User();
            user.setUsername("unsetUsernameOnCreation");
            user.setPass("unsetPassOnCreation");
            user.setBalance(0);
            user.setIs_admin(0);

            if (usernameField.getText() != null && !usernameField.getText().trim().equals(""))
                user.setUsername(usernameField.getText());
            if (passField.getText() != null && !passField.getText().trim().equals(""))
                user.setPass(passField.getText());
            if (balanceField.getText() != null && !balanceField.getText().trim().equals(""))
                user.setBalance(Integer.parseInt(balanceField.getText()));
            if (is_adminField.getText() != null && !is_adminField.getText().trim().equals(""))
                user.setIs_admin(Integer.parseInt(is_adminField.getText()));

            userDao.insert(user);

            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/usersView.fxml"));
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
            //if(user.getIsAdmin == 1)
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
