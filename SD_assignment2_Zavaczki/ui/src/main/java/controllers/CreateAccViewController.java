package controllers;

import Commands.user.CreateAccCommand;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAccViewController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passField;

    @FXML
    private PasswordField passRepField;

    @FXML
    private Button createAccButton;

    @FXML
    private Button backButton;

    @FXML
    public void createAcc() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            CreateAccCommand createAccCommand =
                    new CreateAccCommand(usernameField.getText(), passField.getText(), passRepField.getText());

            if(createAccCommand.execute() == 0) {
                fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/loginView.fxml"));
                root = fxmlLoader.load();

                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goBackToLogin() {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene;
        Parent root;
        FXMLLoader fxmlLoader;

        try {
            fxmlLoader = new FXMLLoader(this.getClass().getResource("/view/loginView.fxml"));
            root = fxmlLoader.load();

            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
