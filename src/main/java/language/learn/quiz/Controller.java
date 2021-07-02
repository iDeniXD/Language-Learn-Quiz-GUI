package language.learn.quiz;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import language.learn.quiz.game.game.Game;
import language.learn.quiz.game.settings.GameSettings;
import language.learn.quiz.hallOfFame.HallOfFame;


public class Controller {
    public void GameSettingsStart(ActionEvent actionEvent) {
//        GameSettings.start();
        Game.start();
    }

    public void HallOfFameShow(ActionEvent actionEvent) {
        HallOfFame.show();
    }

    public void MainExit(ActionEvent actionEvent) {
        Main.currentStage.close();
    }
}
