package language.learn.quiz.game;

import language.learn.quiz.gameSetup.GameSetup;
import language.learn.quiz.process.GameProcess;

public class Game {
    public static void start() {
        setupGame();
    }
    private static void setupGame() {
        // Game start with its setup
        GameSetup.loadScene();
    }

    public static void start(short difficultyChosen, short typeOfGameChosen, Boolean partOfSpeechChosen, String usernameChosen) {
        GameProcess.loadScene(difficultyChosen,
                typeOfGameChosen,
                partOfSpeechChosen,
                usernameChosen);
    }
}
