package language.learn.quiz.gameEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import language.learn.quiz.gameLobby.GameLobby;

public class GameEndController {
    // Nodes that will be used in the class methods
    @FXML
    Label nameLabel,numberOfWordsGivenLabel,pointsLabel,usingPartsOfSpeechLabel,dateLabel;
    @FXML
    GridPane contentGridPane;
    @FXML
    Button okButton;
    @FXML
    AnchorPane rootAnchorPane;

    @FXML
    public void initialize() {
        setup();
    }

    private void setup() {
        setupLabels();
    }

    private void setupLabels() {
        // Simply show the result user has achieved
        nameLabel.setText(GameEnd.state.name);
        numberOfWordsGivenLabel.setText(String.valueOf(GameEnd.state.numberOfWords));
        pointsLabel.setText(String.valueOf(GameEnd.state.points));
        usingPartsOfSpeechLabel.setText((GameEnd.state.usingPartsOfSpeech ? "Yes" : "No"));
        dateLabel.setText(GameEnd.state.date);
    }

    public void goToLobby(ActionEvent actionEvent) {
        GameLobby.loadScene();
    }
}
