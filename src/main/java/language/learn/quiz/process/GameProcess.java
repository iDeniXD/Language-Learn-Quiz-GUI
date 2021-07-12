package language.learn.quiz.process;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import language.learn.quiz.Main;
import language.learn.quiz.CSV.CSVFileWithWords.CSVFileWithWords;
import language.learn.quiz.lobby.Lobby;
import language.learn.quiz.result.GameState;
import language.learn.quiz.user.User;
import language.learn.quiz.word.Word;

import java.io.IOException;
import java.util.Objects;

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
    public static void resetScene(){
        root = null;
    }















//    public static int throwWords(int numberOfWords, int typeOfGame, boolean usingPartsOfSpeech) {
//        GameProcessController.start();
//        // word - random word from CSV file
//        Word word;
//        //result - points, etc.
//        int points = 0;
//
//        // translation - if has been translated correctly
//        boolean translation;
//        // type - if type has been named correctly
//        boolean type;
//
//        for (int i = 0; i < numberOfWords; i++) {
//            // Get random word from CSV file
//            word = CSVFileWithWords.getWord(typeOfGame);
//            // Ask user the translation and type(if needed). True if correct
//            translation = User.isCorrectAnswer(word);
//            // If correct point++
//            if (translation) {
//                if (usingPartsOfSpeech) {
//                    type = User.isCorrectType(word);
//                } else {
//                    type = true;
//                }
//                if (type) {
//                    System.out.println("Correct! You achieved a point! \n");
//                    points++;
//                } else {
//                    System.out.printf("Wrong part of speech! It was a %s. Your answer: %s. Your cleaned answer: %s%n%n",word.type,User.typeAnswer,User.wordAnswerCleaned);
//                }
//            } else {
//                System.out.printf("Wrong word! The word was %s. It is %s. Your answer: %s. Your cleaned answer: %s%n%n",word.translation,word.type,User.wordAnswer,User.wordAnswerCleaned);
//            }
//        }
//        return points;
//    }
//
//    private static String getLagnguageToTranslateFrom(int typeOfGame) {
//        return ENG_RUS_mixed.languageFrom(typeOfGame);
//    }
//
//    private static String getLagnguageToTranslateTo(String langFrom) {
//        if (langFrom.equals(Languages.RUS())) return Languages.ENG();
//        else return Languages.RUS();
//    }

}
