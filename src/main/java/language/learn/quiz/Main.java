package language.learn.quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import language.learn.quiz.lobby.Lobby;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    private static Scene scene;
    // Root is used by other classes in order to set it to stage (usually in methods like "goToLobby")
    public static Parent root;
    // Stage must be accessed by other classes in order to switch scene
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        //Lobby scene
        setScene();
        //Default stage properties are getting set here
        setStage(primaryStage);

        // Load lobby scene
        Lobby.loadScene();
    }

    private void setScene() {
        scene = new Scene(new AnchorPane());
        //style.css for all scenes that will be used in the application
        //TODO divide style.css into multiple css files and apply each of them to certain scene
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    private void setStage(Stage primaryStage) {
        primaryStage.setTitle("Language Learn Quiz");
        // TODO create icon
//        primaryStage.getIcons().add(new Image(""));
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(650);
        primaryStage.setWidth(650);
        primaryStage.setHeight(650);
        primaryStage.show();
        stage = primaryStage;
    }

    public static void setRoot(Parent root) {
        stage.getScene().setRoot(root);
    }

}
