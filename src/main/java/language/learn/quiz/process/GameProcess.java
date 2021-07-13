package language.learn.quiz.process;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import language.learn.quiz.Main;
import language.learn.quiz.game.Game;
import language.learn.quiz.state.GameState;

import java.io.IOException;

public class GameProcess {
    public static GameState state;
    public static void loadScene(short difficultyChosen, short typeOfGameChosen, Boolean partOfSpeechChosen, String usernameChosen) {
        state = new GameState();
        state.numberOfWords = difficultyChosen;
        state.typeOfGame = typeOfGameChosen;
        state.usingPartsOfSpeech = partOfSpeechChosen;
        state.name = usernameChosen;

        // Setting new scene means stage properties will get reset, thus method sets new root
        loadRoot();
    }
    static Parent root = null;
    private static void loadRoot() {
        root = getRoot();
        show(root);
    }

    // Get root: GameProcess root.
    private static Parent getRoot() {
        try {
            root = FXMLLoader.load(Main.class.getResource("gameProcess/GameProcess.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return root;
    }
    // Show the root: set it as scene of the stage new root.
    public static void show(Parent root) {
        Main.setRoot(root);
    }

    public static void gameEnd() {
        Game.end(state);
    }
}
