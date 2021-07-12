package language.learn.quiz.lobby;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import language.learn.quiz.Main;

import java.io.IOException;

public class Lobby {

    public static void loadScene() {
        // Setting new scene means stage properties will get reset, thus method sets new root
        loadRoot();
    }
    static Parent root = null;
    private static void loadRoot() {
        root = getRoot();
        show(root);
    }

    // Get root: GameSetup root.
    private static Parent getRoot() {
        if (root == null){
            try {
                root = FXMLLoader.load(Main.class.getResource("lobby/Lobby.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return root;
    }

    private static void show(Parent root) {
        Main.setRoot(root);
    }
}
