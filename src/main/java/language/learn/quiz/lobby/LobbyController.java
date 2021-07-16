package language.learn.quiz.lobby;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import language.learn.quiz.Main;
import language.learn.quiz.game.Game;
import language.learn.quiz.gameSetup.GameSetup;
import language.learn.quiz.hallOfFame.HallOfFame;
import language.learn.quiz.nodes.PointsLabel;

import java.util.Random;


public class LobbyController {
    public void Play(ActionEvent actionEvent) {
        Game.start();
    }

    public void HallOfFame(ActionEvent actionEvent) {
        HallOfFame.show();
    }

    public void Exit(ActionEvent actionEvent) {
        Main.stage.close();
    }
}
