package controllers;

import Commands.user.LoginCommand;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccButton;

    @FXML
    public void login()
    {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        Scene scene;
        Parent root = null;
        FXMLLoader fxmlLoader;

        try
        {
        LoginCommand loginCommand = new LoginCommand(usernameField.getText(), passField.getText());

        User currentUser = loginCommand.execute();

        System.out.println(currentUser.getUsername()+" "+currentUser.getPass());

        if(currentUser != null)
            if(currentUser.getIs_admin() == 1)
            {
                fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/choiceAdminView.fxml"));
                root = fxmlLoader.load();
            }
            else
            {
                fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/emptyView.fxml"));
                root = fxmlLoader.load();
            }

        // show next view
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToCreateAcc()
    {
        Stage stage = (Stage) createAccButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/createAccView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
