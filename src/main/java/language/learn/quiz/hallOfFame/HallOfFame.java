package language.learn.quiz.hallOfFame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import language.learn.quiz.Main;

import java.io.IOException;


public class HallOfFame {
    static Parent root;
    public static void show() {
        try {
            root = getRoot();
            show(root);
            // Scene shows up, then FXMLLoader calls method initialize(), which continues program
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Parent getRoot() throws IOException {
        if (root == null){
            root = FXMLLoader.load(HallOfFame.class.getResource("HallOfFame.fxml"));
        }
        return root;
    }
    private static void show(Parent root) {
        Main.setRoot(root);
    }

    // When new record is made, the scene needs to be reloaded
    public static void resetScene() {
        root = null;
    }

}
