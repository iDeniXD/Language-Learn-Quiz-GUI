package language.learn.quiz.game.start;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import language.learn.quiz.Main;

import java.io.IOException;

public class GameProcessController {
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

    static Parent root = null;
    public static void start() {
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
            root = FXMLLoader.load(Main.class.getResource("gameProcess/GameProcess.fxml"));
        }
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
        enableFields();
        changeAction();
        nextWord(new ActionEvent());
    }

    private void changeAction() {
        nextWord.setOnAction(GameProcessController::nextWord);
    }

    private void enableFields() {
        root.getChildrenUnmodifiable().forEach(i -> {
            i.setDisable(false);
        });
    }

    private void changeLabel() {
        nextWord.setText("Next word");
    }

    private static void nextWord(ActionEvent actionEvent) {

    }
}
