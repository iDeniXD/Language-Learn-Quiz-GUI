package language.learn.quiz.gameLobby;

import javafx.event.ActionEvent;
import language.learn.quiz.Main;
import language.learn.quiz.game.Game;
import language.learn.quiz.hallOfFame.HallOfFame;


public class GameLobbyController {
    // Lobby buttons
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
