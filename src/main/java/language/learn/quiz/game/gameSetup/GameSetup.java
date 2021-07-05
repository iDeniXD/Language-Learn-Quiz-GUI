package language.learn.quiz.game.gameSetup;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import language.learn.quiz.Main;
import language.learn.quiz.game.difficulty.Difficulty;
import language.learn.quiz.game.result.GameResult;
import language.learn.quiz.game.start.GameStart;
import language.learn.quiz.game.typeOfGame.ENG_RUS_mixed;
import language.learn.quiz.game.user.User;

import java.io.IOException;
import java.util.HashMap;

public class GameSetup {
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
            root = FXMLLoader.load(Main.class.getResource("gameSetup/GameSetup.fxml"));
        }
        return root;
    }
    private static void show(Parent scene) {
        Main.stage.getScene().setRoot(root);
    }

    @FXML
    ToggleGroup difficulty;
    @FXML
    ToggleGroup typeOfGame;
    @FXML
    ToggleButton partOfSpeech;
    @FXML
    TextField username;
    @FXML
    public void initialize() {
        setup();
    }

    private void setup() {
        setDifficultiesText();
        setTypeOfGameText();
        setListeners();
    }

    private static Short difficultyChosen;
    private static Short typeOfGameChosen;
    private static Boolean partOfSpeechChosen = false;
    private static String usernameChosen;
    private static final HashMap<Control,String> objectsToChoose = new HashMap<>();
    private void setListeners() {

        objectsToChoose.put((Control)difficulty.getToggles().get(0),"Choose one");
        objectsToChoose.put((Control)typeOfGame.getToggles().get(0),"Choose one");
        objectsToChoose.put(username,"Empty field!");

        difficulty.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
            difficultyChosen = Difficulty.getDifficultyIndex(((RadioButton) difficulty.getSelectedToggle()).getText());
            objectsToChoose.remove(difficulty.getToggles().get(0));
        });
        typeOfGame.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
            typeOfGameChosen = ENG_RUS_mixed.getTypeIndex(((RadioButton)typeOfGame.getSelectedToggle()).getText());
            objectsToChoose.remove(typeOfGame.getToggles().get(0));
        });
        partOfSpeech.selectedProperty().addListener((observable, oldValue, newValue) -> {
            partOfSpeechChosen = partOfSpeech.isSelected();
        });
        username.textProperty().addListener((observable, oldValue, newValue)->{
            if (User.checkAvailability(username.getText())) {
                usernameChosen = username.getText();
                objectsToChoose.remove(username);
            }else {
                usernameChosen = null;
                objectsToChoose.put(username,(username.getText().equals("") ? "Empty field!" : "This username is already taken!"));
            }
        });
    }

    private void setDifficultiesText() {
        difficulty.getToggles().forEach(button->{
            ((RadioButton) button).setText(Difficulty.getDifficulty(Integer.parseInt(((RadioButton)button).getText().strip())));
        });
    }

    private void setTypeOfGameText() {
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
        Main.stage.getScene().setRoot(Main.root);
    }

    public void launchGame(ActionEvent actionEvent) {
        if (!highlight(objectsToChoose)) {
            GameStart.start(Difficulty.getWords().get(difficultyChosen.intValue()).shortValue(),
                    typeOfGameChosen,
                    partOfSpeechChosen,
                    usernameChosen);
        }
    }
    @FXML
    AnchorPane rootAnchorPane;
    private boolean highlight(HashMap<Control,String> objectsToChoose) {
        boolean result = false;
        Label label;
        Bounds boundsInScene;
        for (Control o : objectsToChoose.keySet()) {
            result = true;
            label = new Label(objectsToChoose.get(o));

            boundsInScene = o.localToScene(o.getBoundsInLocal());
            label.setLayoutX(boundsInScene.getMinX());
            label.setLayoutY(boundsInScene.getMinY());

            label.setId("WarningText");

            rootAnchorPane.getChildren().add(label);

            Label finalLabel = label;
            Main.stage.widthProperty().addListener((observable, oldValue, newValue)->{
                rootAnchorPane.getChildren().remove(finalLabel);
            });
            Main.stage.heightProperty().addListener((observable, oldValue, newValue)->{
                rootAnchorPane.getChildren().remove(finalLabel);
            });
            try {
                ((RadioButton)o).getToggleGroup().selectedToggleProperty().addListener((observable, oldValue, newValue)->{
                    rootAnchorPane.getChildren().remove(finalLabel);
                });
            } catch (Exception e){
                ((TextField)o).textProperty().addListener((observable, oldValue, newValue)->{
                    rootAnchorPane.getChildren().remove(finalLabel);
                });
            }
        }
        return result;
    }
}
