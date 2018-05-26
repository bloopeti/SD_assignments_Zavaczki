import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Stage window;
            Scene currentScene;//, loginScene, createAccScene;
            String windowName = "tournament manager";
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
    