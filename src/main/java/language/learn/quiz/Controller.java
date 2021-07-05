package language.learn.quiz;
import javafx.event.ActionEvent;
import language.learn.quiz.game.gameSetup.GameSetup;
import language.learn.quiz.hallOfFame.HallOfFame;


public class Controller {
    public void GameSettingsStart(ActionEvent actionEvent) {
        GameSetup.start();
    }

    public void HallOfFameShow(ActionEvent actionEvent) {
        HallOfFame.show();
    }

    public void MainExit(ActionEvent actionEvent) {
        Main.stage.close();
    }
}
