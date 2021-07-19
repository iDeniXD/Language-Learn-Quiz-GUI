package language.learn.quiz.gameEnd;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import language.learn.quiz.Main;
import language.learn.quiz.gameState.GameState;

import java.io.IOException;

public class GameEnd {
    public static GameState state;
    public static void loadScene(GameState s) {
        state = s;
        // Setting new scene means stage properties will get reset, thus method sets new root
        loadRoot();
    }

    static Parent root = null;
    private static void loadRoot() {
        root = getRoot();
        show(root);
    }

    private static Parent getRoot() {
        try {
            root = FXMLLoader.load(Main.class.getResource("gameEnd/GameEnd.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }

    private static void show(Parent root) {
        Main.setRoot(root);
    }
}
