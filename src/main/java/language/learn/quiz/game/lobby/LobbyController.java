package language.learn.quiz.game.lobby;
import javafx.event.ActionEvent;
import language.learn.quiz.Main;
import language.learn.quiz.game.gameSetup.GameSetup;
import language.learn.quiz.hallOfFame.HallOfFame;


public class LobbyController {
    public void Play(ActionEvent actionEvent) {
//        Game.start();
        GameSetup.start();
    }

    public void HallOfFame(ActionEvent actionEvent) {
        HallOfFame.show();
    }

    public void Exit(ActionEvent actionEvent) {
        Main.stage.close();
    }
}
