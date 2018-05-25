import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import model.User;

import java.io.IOException;

public class App extends Application {

    Stage window;
    Scene currentScene;//, loginScene, createAccScene;
    String windowName = "Tournament manager";
    User currentUser;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));

            currentScene = new Scene(root);

            window = primaryStage;
            window.setTitle(windowName);
            window.setScene(currentScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    