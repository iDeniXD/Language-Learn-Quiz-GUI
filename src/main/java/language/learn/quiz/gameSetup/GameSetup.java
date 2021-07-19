package language.learn.quiz.gameSetup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import language.learn.quiz.Main;
import language.learn.quiz.difficulty.Difficulty;
import language.learn.quiz.game.Game;

import java.io.IOException;

public class GameSetup {
    public static void loadScene() {
        // Setting new scene means stage properties will get reset, thus method sets new root
        loadRoot();
    }

    static Parent root = null;
    private static void loadRoot() {
        root = getRoot();
        show(root);
        // Futher actions will continue in GameSetupController method initialize()
    }

    // Get root: GameSetup root.
    private static Parent getRoot() {
        if (root == null){
            try {
                root = FXMLLoader.load(Main.class.getResource("gameSetup/GameSetup.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return root;
    }
    // Show the root: set it as scene of the stage new root.
    private static void show(Parent root) {
        Main.setRoot(root);
    }
    // When new record appears or user ends the game
    public static void resetScene(){
        root = null;
    }


    // When game is setup
    public static void launchGame() {
        // These variables - game options/settings
        Game.start(
                Difficulty.getWords().get(GameSetupController.difficultyChosen.intValue()).shortValue(),
                GameSetupController.typeOfGameChosen,
                GameSetupController.partOfSpeechChosen,
                GameSetupController.usernameChosen);
        // Reload game setup
        GameSetup.resetScene();
    }
}
