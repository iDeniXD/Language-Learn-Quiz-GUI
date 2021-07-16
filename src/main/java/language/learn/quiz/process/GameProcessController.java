package language.learn.quiz.process;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import language.learn.quiz.CSV.CSVFileWithWords.CSVFileWithWords;
import language.learn.quiz.nodes.AlertLabel;
import language.learn.quiz.lobby.Lobby;
import language.learn.quiz.nodes.PointsLabel;
import language.learn.quiz.user.User;
import language.learn.quiz.word.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameProcessController {
    // Nodes that will be used in the class methods
    @FXML
    AnchorPane rootAnchorPane;
    @FXML
    Button nextWord, backToLobby;
    @FXML
    Label givenWord, partOfSpeechLabel, translationLabel, wordInTheCount;
//    @FXML
//    Label points;
    @FXML
    TextField partOfSpeechField, translationField;
    @FXML
    GridPane contentGridPane, pointsGridPane;


    @FXML
    public void initialize(){
        setup();
    }

    private void setup() {
        removeUnneededNodes();
    }
    private void removeUnneededNodes() {
        // If user has chosen option not to guess type of speech, then these nodes need to be removed
        if (partOfSpeechNodesUnneeded())
            removePartOfSpeechNodes();
    }
    private boolean partOfSpeechNodesUnneeded() {
        return !GameProcess.state.usingPartsOfSpeech;
    }
    private void removePartOfSpeechNodes() {
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
        setListeners();
        setNodes();
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

    private void setListeners() {
        var classStyle = "pressed";
        GameProcess.root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    nextWord.getStyleClass().remove(classStyle);
                    nextWord();
                }
            }
        });
        GameProcess.root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                nextWord.getStyleClass().remove(classStyle);
                if (event.getCode() == KeyCode.ENTER) {
                    nextWord.getStyleClass().add(classStyle);
                }
            }
        });
    }
    public PointsLabel points;
    private void setNodes() {
        points = new PointsLabel("0");
        pointsGridPane.add(points,1,0);
        points.getStyleClass().add(("counters"));
    }

    private void nextWord() {
        checkIfCorrect();
        if (isGameEnd()) {
            gameEnd();
        } else {
            giveNextWord();
        }
    }

    Word word;
    private void checkIfCorrect() {
        if (word == null) {
            return;
        }
        if (User.isCorrectTranslation(word,translationField.getText())) {
            if (GameProcess.state.usingPartsOfSpeech) {
                if (User.isCorrectPart(word,partOfSpeechField.getText())) {
                    setPoints();
                }
            } else {
                setPoints();
            }
        }
    }

    private void setPoints() {
        GameProcess.state.points++;
        points.setText(String.valueOf(GameProcess.state.points));
    }

    private void giveNextWord() {
        word = getWord();
        showWord();
        showClarification();
        setupTextNodes();
        focusOnFirstTextField();
    }

    // Run these Runnables (alertLabel disappearing) when new word given, meaning no more need in clarification popup
    List<Runnable> runWhenNewWordGiven = new ArrayList<>();
    private Word getWord() {
        runWhenNewWordGiven.forEach(Runnable::run);
        return CSVFileWithWords.getWord();
//        return CSVFileWithWords.getWord(GameProcess.state.typeOfGame);
    }

    private void showWord() {
        givenWord.setText(word.original);
    }

    private void showClarification() {
        // If word has its clarification, than it gets shown as alert notification
        if (!word.clarification.equals("")) {
            createClarificationLabel();
        }
    }

    private void createClarificationLabel() {
        // Create label
        AlertLabel alertLabel = new AlertLabel(word.clarification);
        // Set its position relatively to the Label givenWord
        alertLabel.setPositionRelatively(givenWord);

        // Add label to scene
        rootAnchorPane.getChildren().add(alertLabel);

        // It will disappear after certain time (depends on the number of words) or after event happens (User presses Enter button)
        alertLabel.setTimerToSelfDestruct(rootAnchorPane, getTimeToRead(word.clarification));
        createListener(alertLabel);
    }

    private long getTimeToRead(String line) {
        return 300*(line.chars().filter(ch -> ch == ' ').count()+1) + 3000;
    }

    private void createListener(AlertLabel alertLabel) {
        runWhenNewWordGiven.add(() -> alertLabel.disappear(rootAnchorPane.getChildren()));
    }

    private void setupTextNodes() {
        //Setup translation label
        translationLabel.setText("Translate the given word to %s".formatted(word.translateToLanguage));
        //Setup label that counts given words
        wordInTheCount.setText((Integer.parseInt(wordInTheCount.getText().split("/")[0].strip()) + 1) +" / "+ GameProcess.state.numberOfWords);
        //Setup text input fields
        translationField.setText("");
        partOfSpeechField.setText("");
    }

    private void focusOnFirstTextField() {
        translationField.requestFocus();
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
