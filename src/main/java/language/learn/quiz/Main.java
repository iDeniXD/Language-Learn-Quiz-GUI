package language.learn.quiz;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import language.learn.quiz.lobby.Lobby;
import language.learn.quiz.transitions.Transitions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

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
        scene = new Scene(new AnchorPane(), Color.rgb(125,111,134));
    }

    private void setStage(Stage primaryStage) {
        primaryStage.setTitle("Language Learn Quiz");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(650);
        primaryStage.setMinWidth(722);
        primaryStage.setWidth(650);
        primaryStage.setHeight(722);
        primaryStage.getIcons().add(new Image(String.valueOf(getClass().getResource("icon/icon.png"))));
        primaryStage.show();
        stage = primaryStage;
    }

    public static void setRoot(Parent root) {
        stage.getScene().setRoot(root);
        setupRoot(root);
    }

    private static void setupRoot(Parent root) {
        Transitions.rootTransitionAppear(root);
        root.requestFocus();
    }

}
