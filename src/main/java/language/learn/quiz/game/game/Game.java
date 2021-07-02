package language.learn.quiz.game.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import language.learn.quiz.Main;
import language.learn.quiz.game.difficulty.Difficulty;
import language.learn.quiz.game.typeOfGame.ENG_RUS_mixed;

import java.io.IOException;
import java.net.URL;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class Game {
    static Scene scene = null;
    public static void start() {
        try {
            scene = getScene();
            show(scene);
            // Scene shows up, then FXMLLoader calls method initialize(), which continues program
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Scene getScene() throws IOException {
        if (scene == null){
            Parent FXMLFile = FXMLLoader.load(Main.class.getResource("game/GameSetup.fxml"));
            scene = new Scene(FXMLFile);
            scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        }
        return scene;
    }
    private static void show(Scene scene) {
        Main.currentStage.setScene(scene);
    }


    @FXML
    public void initialize() {
        setup(scene);
    }

    private void setup(Scene scene) {
        setDifficulties();
        setTypeOfGame();
    }
    @FXML
    ToggleGroup words;
    private void setDifficulties() {
        words.getToggles().forEach(button->{
            ((RadioButton) button).setText(Difficulty.getDifficulty(Integer.parseInt(((RadioButton)button).getText().strip())));
        });
    }

    @FXML
    ToggleGroup typeOfGame;
    private void setTypeOfGame() {
        typeOfGame.getToggles().forEach(button->{
            ((RadioButton) button).setText(ENG_RUS_mixed.getType(Integer.parseInt(((RadioButton)button).getText().strip())));
        });
    }

    //When toggleButton is pressed, it changes its label
    public void changeText(ActionEvent actionEvent) {
        var button = (ToggleButton)actionEvent.getSource();
        button.setText((button.getText().equals("Yes") ? "No" : "Yes"));
    }

    //Button EXIT
    public void goToMainMenu(ActionEvent actionEvent) {
        Main.currentStage.setScene(Main.menuScene);
    }

    public void launchGame(ActionEvent actionEvent) {

    }
}
