package language.learn.quiz.game.settings;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import language.learn.quiz.Main;
import language.learn.quiz.game.result.GameResult;

import java.io.IOException;

public class GameSettings {

    static Scene gameSettingsScene = null;

    @FXML
    ToggleGroup words;
    @FXML
    ToggleGroup typeOfGame;
    @FXML
    ToggleButton partOfSpeech;
    @FXML
    TextField username;

    public static void start() {

        try {
            showScene();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            System.out.println(1/0);
//            // Get difficulty
//            result.numberOfWords = Difficulty.getNumberOfWords();
//            // Get type of game: ENG to RUS, RUS to ENG, mixed
//            result.typeOfGame = ENG_RUS_mixed.getType();
//            // Will user also be required to input a part of speech of the word
//            result.usingPartsOfSpeech = PartsOfSpeech.getUsingPartsOfSpeech();
//            // Get user's name
//            result.name = User.getName();
//            // Method start game: throwing words and receiving from user translation
//            result.points = GameStart.throwWords(result.numberOfWords, result.typeOfGame, result.usingPartsOfSpeech);
//        } catch (Exception e){
//            result.numberOfWords = 1;
//            result.typeOfGame = 1;
//            result.usingPartsOfSpeech = true;
//            result.name = "d";
//            result.points = GameStart.throwWords(result.numberOfWords, result.typeOfGame, result.usingPartsOfSpeech);
//            // Output result
//            result.show();
//            result.record();
////            Menu.show();
//        }
        //date = ...;
        //User.createNewRecord(result.time,name,date);
    }


    //Load scene
    private static void showScene() throws IOException {
        if (gameSettingsScene == null){
            Parent FXMLFile = FXMLLoader.load(Main.class.getResource("game/GameSetup.fxml"));
            gameSettingsScene = new Scene(FXMLFile);
            gameSettingsScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            loadNeeded(gameSettingsScene);
        }
        Main.stage.setScene(gameSettingsScene);
    }

    @FXML
    private static void loadNeeded(Scene gameSettingsScene) {
        ToggleGroup typeOfGame = new ToggleGroup();
        System.out.println(typeOfGame.getToggles());
    }

    //Button EXIT
    public void goToMainMenu(ActionEvent actionEvent) {
        Main.stage.getScene().setRoot(Main.root);
    }

    //When toggleButton is pressed, it changes its label
    public void changeText(ActionEvent actionEvent) {
        var button = (ToggleButton)actionEvent.getSource();
        button.setText((button.getText().equals("Yes") ? "No" : "Yes"));
    }

    @FXML
    public void launchGame(ActionEvent actionEvent) {
        var result = new GameResult();
//        result.numberOfWords = Difficulty.getNumberOfWords(words.getSelectedToggle());
//        result.typeOfGame = ENG_RUS_mixed.getType(typeOfGame.getSelectedToggle());
//        result.usingPartsOfSpeech = PartsOfSpeech.getUsingPartsOfSpeech(partOfSpeech);
//        result.name = User.getName(username);
    }
}
