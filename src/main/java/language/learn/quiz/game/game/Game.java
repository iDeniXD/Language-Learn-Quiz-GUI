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
    public static void start() {
        setScene();
        // Scene shows up, then FXMLLoader calls method initialize(), which continues program
    }
    static Scene scene = null;
    private static void setScene() {
        try {
            scene = getScene();
            show(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static private Scene getScene() throws IOException {
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

//    public static void start(){
//        var game = new Game();
//        game.setScene();
//        game.launch();
//    }
//
//    static Scene scene = null;
//    public void setScene() {
//        try {
//            scene = getScene();
//            setup(scene);
//            show(scene);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void show(Scene scene) {
//        Main.currentStage.setScene(scene);
//    }
//
//    @FXML
//    ToggleGroup typeOfGame;
//    @FXML
//    ToggleButton partOfSpeech;
//
//    private void setup(Scene scene) {
//        System.out.println(typeOfGame);
////        System.out.println(((RadioButton)scene.lookup("#PlainText")).getText());
////        System.out.println(partOfSpeech.getText());
////        for (Toggle button :
////                typeOfGame.getToggles()) {
////            System.out.println(button);
////            ((RadioButton) button).setText(ENG_RUS_mixed.getType(Integer.parseInt(((RadioButton)button).getText().strip())));
////        }
//
//    }
//
//    static private Scene getScene() throws IOException {
//        if (scene == null){
//            Parent FXMLFile = FXMLLoader.load(Main.class.getResource("game/GameSetup.fxml"));
//            scene = new Scene(FXMLFile);
//            scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
//        }
//        return scene;
//    }
//
//    private void launch() {
//    }
//
//    //Button EXIT
//    public void goToMainMenu(ActionEvent actionEvent) {
//        Main.currentStage.setScene(Main.menuScene);
//    }
//
//    //When toggleButton is pressed, it changes its label
//    public void changeText(ActionEvent actionEvent) {
//        var button = (ToggleButton)actionEvent.getSource();
//        button.setText((button.getText().equals("Yes") ? "No" : "Yes"));
//    }
//
//    public void launchGame(ActionEvent actionEvent) {
//    }
}
