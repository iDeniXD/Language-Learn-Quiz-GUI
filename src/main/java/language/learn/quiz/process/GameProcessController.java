package language.learn.quiz.process;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import language.learn.quiz.CSV.CSVFileWithWords.CSVFileWithWords;
import language.learn.quiz.lobby.Lobby;
import language.learn.quiz.user.User;
import language.learn.quiz.word.Word;

import java.util.Objects;

public class GameProcessController {
    // Nodes that will be used in the class methods
    @FXML
    AnchorPane rootAnchorPane;
    @FXML
    Button nextWord, backToLobby;
    @FXML
    Label givenWord, partOfSpeechLabel, translationLabel, points, wordInTheCount;
    @FXML
    TextField partOfSpeechField, translationField;
    @FXML
    GridPane contentGridPane;


    @FXML
    public void initialize(){
        setup();
    }

    private void setup() {
        removeUnneededNodes();
    }
    private void removeUnneededNodes() {
        // If user has chosen option not to guess type of speech, then these nodes need to be removed
        if (typeOfSpeechNodesUnneeded())
            removeTypeOfSpeechNodes();
    }
    private boolean typeOfSpeechNodesUnneeded() {
        return !GameProcess.state.usingPartsOfSpeech;
    }
    private void removeTypeOfSpeechNodes() {
        // Remove third row of the GridPane with its children - these are nodes for game with choosing type of game of a given word
        contentGridPane.getChildren().remove(partOfSpeechLabel);
        contentGridPane.getChildren().remove(partOfSpeechField);
        contentGridPane.getRowConstraints().remove(3);
    }

    // Button Back to Lobby
    public void goToLobby(ActionEvent actionEvent) {
        Lobby.loadScene();
    }

    public void StartGame(ActionEvent actionEvent) {
        // At the beginning each node which will be used in the game is disabled
        enableFields(contentGridPane);
        // When game starts:
        // Back to Lobby -> End game
        // Start! -> Next word
        changeButtons();
        // Game starts with a word given to user
        nextWord();
    }

    private void enableFields(GridPane gp) {
        gp.getChildren().forEach(i -> {
            i.setDisable(false);
            // If a child is a GridPane - go through its children recursively too
            if (i instanceof GridPane) {
                enableFields((GridPane) i);
            }
        });
    }

    private void changeButtons() {
        //Button nextWord
        nextWord.setText("Next word");
        nextWord.setOnAction(event -> nextWord());

        //Button backToLobby
        backToLobby.setText("End game");
        backToLobby.setOnAction(event -> {
            Lobby.loadScene();
        });
    }

    private void nextWord() {
        if (isGameEnd()) {
            gameEnd();
        } else {
            checkIfCorrect();
            giveNextWord();
        }
    }

    Word word;
    private void checkIfCorrect() {
        if (Objects.isNull(word)) {
            return;
        }
        if (User.isCorrectAnswer(word,translationField.getText())) {
            if (GameProcess.state.usingPartsOfSpeech) {
                if (User.isCorrectPart(word,partOfSpeechField.getText())) {
                    setPoints();
                } else {
                    System.out.printf("Correct part: %s%nYour answer: %s%n",word.type,partOfSpeechField.getText());
                }
            } else {
                setPoints();
            }

        } else {
            System.out.printf("Correct answer: %s%nYour answer: %s%n",word.translation,translationField.getText());
        }
    }

    private void setPoints() {
        GameProcess.state.points++;
        points.setText(String.valueOf(GameProcess.state.points));
    }

    private void giveNextWord() {
        word = getWord();
        showWord();
        setupTextNodes();
    }

    private Word getWord() {
        return CSVFileWithWords.getWord(GameProcess.state.typeOfGame);
    }

    private void showWord() {
        givenWord.setText(word.original);
    }

    private void setupTextNodes() {
        //Setup translation label
        translationLabel.setText("Translate the given word to %s".formatted(word.getTranslateTo()));
        //Setup label that counts given words
        wordInTheCount.setText((Integer.parseInt(wordInTheCount.getText().split("/")[0].strip()) + 1) +" / "+ GameProcess.state.numberOfWords);
        //Setup text input fields
        translationField.setText("");
        partOfSpeechField.setText("");
    }


    private boolean isGameEnd() {
        // Game ends when words counter reaches chosen difficulty
        return (Integer.parseInt(wordInTheCount.getText().split("/")[0].strip()) ==
                GameProcess.state.numberOfWords);
    }

    private void gameEnd() {
        GameProcess.gameEnd();
    }
}
