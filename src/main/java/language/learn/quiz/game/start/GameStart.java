package language.learn.quiz.game.start;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import language.learn.quiz.Main;
import language.learn.quiz.game.CSV.CSVFileWithWords.CSVFileWithWords;
import language.learn.quiz.game.result.GameResult;
import language.learn.quiz.game.user.User;
import language.learn.quiz.game.word.Word;

import java.io.IOException;
import java.util.Objects;

public class GameStart {
    private static GameResult result;
    public static void start(short difficultyChosen, short typeOfGameChosen, Boolean partOfSpeechChosen, String usernameChosen) {
        result = new GameResult();
        result.numberOfWords = difficultyChosen;
        result.typeOfGame = typeOfGameChosen;
        result.usingPartsOfSpeech = partOfSpeechChosen;
        result.name = usernameChosen;
        show();
        // futher actions will lead to end of the game (method: gameEnd)
    }
    @FXML
    AnchorPane rootAnchorPane;
    @FXML
    Button nextWord;
    @FXML
    Label givenWord;
    @FXML
    Label partOfSpeechLabel;
    @FXML
    Label translationLabel;
    @FXML
    Label points;
    @FXML
    Label wordInTheCount;
    @FXML
    TextField partOfSpeechField;
    @FXML
    TextField translationField;
    @FXML
    GridPane contentGridPane;

    static Parent root = null;
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
        root = FXMLLoader.load(Main.class.getResource("gameProcess/GameProcess.fxml"));
        return root;
    }

    private static void show(Parent root) {
        Main.stage.getScene().setRoot(root);
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        Main.stage.getScene().setRoot(Main.root);
    }

    public void StartGame(ActionEvent actionEvent) {
        changeLabel();
        enableFields(contentGridPane);
        if (!needed()) {
            removePartsOfSpeechField();
        }
        changeAction();
        nextWord();
    }

    private void changeAction() {
        nextWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nextWord();
            }
        });
    }

    private void enableFields(GridPane gp) {
        gp.getChildren().forEach(i -> {
            i.setDisable(false);
            if (i instanceof GridPane) {
                enableFields((GridPane) i);
            }
        });
    }

    private boolean needed() {
        return result.usingPartsOfSpeech;
    }

    private void removePartsOfSpeechField() {
        contentGridPane.getChildren().remove(partOfSpeechLabel);
        contentGridPane.getChildren().remove(partOfSpeechField);
        contentGridPane.getRowConstraints().remove(3);

    }

    private void changeLabel() {
        nextWord.setText("Next word");
    }

    Word word;
    private void nextWord() {
        if (gameEnded()) {
            gameEnd();
        } else {
            checkIfCorrect();
            giveNextWord();
        }

    }

    private boolean gameEnded() {
        return (Integer.parseInt(wordInTheCount.getText().split("/")[0].strip()) ==
                Integer.parseInt(wordInTheCount.getText().split("/")[1].strip()))
                &&
                (Integer.parseInt(wordInTheCount.getText().split("/")[1].strip()) != 0);
    }

    private void checkIfCorrect() {
        if (Objects.isNull(word)) {
            return;
        }
        if (User.isCorrectAnswer(word,translationField.getText())) {
            if (result.usingPartsOfSpeech) {
                if (User.isCorrectType(word,partOfSpeechField.getText())) {
                    System.out.println("bug");
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
        result.points++;
        points.setText(String.valueOf(result.points));
    }


    private void giveNextWord() {
        word = getWord();
        showWord();
        setupTextNodes();
    }

    private Word getWord() {
        return CSVFileWithWords.getWord(result.typeOfGame);
    }

    private void showWord() {
        givenWord.setText(word.original);
    }

    private void setupTextNodes() {
        //Setup translation label
        translationLabel.setText("Translate the given word to %s".formatted(word.getTranslateTo()));
        //Setup label that counts given words
        wordInTheCount.setText((Integer.parseInt(wordInTheCount.getText().split("/")[0].strip()) + 1) +" / "+result.numberOfWords);
        //Setup text input fields
        translationField.setText("");
        partOfSpeechField.setText("");
    }


    private static void gameEnd(){
        // TODO end game
        // Output result
//        result.show();
//        result.record();
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
