package language.learn.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene scene;
    public static Parent root;
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Load menu and wait for user actions
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        //style.css for all scenes that will be used in the application
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Language Learn Quiz");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(650);
        primaryStage.show();
        stage = primaryStage;
        Main.scene = scene;
    }
}
