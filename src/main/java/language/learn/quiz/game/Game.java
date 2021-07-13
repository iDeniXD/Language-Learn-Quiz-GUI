package language.learn.quiz.game;

import language.learn.quiz.gameEnd.GameEnd;
import language.learn.quiz.gameSetup.GameSetup;
import language.learn.quiz.process.GameProcess;
import language.learn.quiz.state.GameState;

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

    public static void end(GameState state) {
        // Saves new record + reloads hall of fame
        state.record();
        // Show game result
        GameEnd.loadScene(state);
    }
}
