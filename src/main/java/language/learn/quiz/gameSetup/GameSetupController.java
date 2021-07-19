package language.learn.quiz.gameSetup;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import language.learn.quiz.Main;
import language.learn.quiz.difficulty.Difficulty;
import language.learn.quiz.languages.Languages;
import language.learn.quiz.nodes.AlertLabel;
import language.learn.quiz.gameLobby.GameLobby;
import language.learn.quiz.user.User;
import language.learn.quiz.word.Word;

import java.util.HashMap;

public class GameSetupController {
    // Nodes that will be used in the class methods
    @FXML
    ToggleGroup difficultyToggles, typeOfGameToggles;
    @FXML
    ToggleButton partOfSpeechToggle;
    @FXML
    TextField username;
    @FXML
    AnchorPane rootAnchorPane;
    @FXML
    Button playButton;
    @FXML
    VBox difficultyTogglesVBox, typeOfGameTogglesVBox, usernameTextfieldVBox;


    @FXML
    public void initialize() {
        setup();
    }

    private void setup() {
        setupLabels();
        setupListeners();
    }

    private void setupLabels() {
        setDifficultiesText();
        setTypeOfGameText();
    }

    private void setDifficultiesText() {
        // These radioButtons text is 0/1/2 which is index of difficulty.
        // When scene is being set, the text of the buttons needs to be replaced to an appropriate difficulty
        difficultyToggles.getToggles().forEach(button->
                ((RadioButton) button).setText(
                        Difficulty.getDifficultyByIndex(
                        Integer.parseInt(((RadioButton)button) // Index taken as Integer
                                .getText() // 0/1/2
                                .strip()) // Text might have redudant spaces
                        )
                )
        );
    }
    private void setTypeOfGameText() {
        // These radioButtons text is 1/2/3 which is index of a type of the game (RUS-ENG,ENG-RUS,MIXED).
        // When scene is being set, the text of the buttons needs to be replaced to an appropriate type of the game
        typeOfGameToggles.getToggles().forEach(button->
                ((RadioButton) button).setText(
                        Languages.getTypeOfGameAsTitle(
                                Integer.parseInt(((RadioButton)button) // Index taken as Integer
                                        .getText() // 0/1/2
                                        .strip()) // Text might have redudant spaces
                        )
                )
        );
    }

    // User chooses game settings and they get saved in these variables:
    static public Short difficultyChosen;
    static public Short typeOfGameChosen;
    static public Boolean partOfSpeechChosen;
    static public String usernameChosen;

    // objectsToHighlight - explained in the further comment
    private static final HashMap<VBox,String> objectsToHighlight = new HashMap<>();
    private void setupListeners() {
        // Set listeners for selection nodes

        // First of all, selected option will get saved in appropriate variable (difficultyChosen, typeOfGameChosen, partOfSpeechChosen, usernameChosen)
        // Second of all, listeners also work with objectsToHighlight:

        // If RadioButtons get selected, then their parent (VBox) gets removed from list objectsToHighlight, because
        // they will no longer have to be highlighted in order to draw user's attention to it, thus letting him know,
        // that he can't start game w/o selecting them

        // If it is TextField, than it has to get removed every time user chooses available username and
        // get put back in the list if user chooses unavailable username or just cleans the TextField (empty username)

        // If it is ToggleButton, than the only thing its listener has to do is to save the selected option into the variable (partOfSpeechChosen)

        setDiffificultyTogglesListener();
        setTypeOfGameTogglesListener();
        setPartOfSpeechToggleListener();
        setUsernameTextFieldListener();

        // list which has VBoxes as a key and a message as value.
        // It is required when is trying to start game w/o selecting all the needed fields (like RadioButtons and TextField)
        // If some of them are not selected and use is trying to start the game, program will highlight VBoxes of unselected nodes with its message
        // If the list is empty, that means that all the nodes were selected and game may start
        objectsToHighlight.put(difficultyTogglesVBox,"Choose one");
        objectsToHighlight.put(typeOfGameTogglesVBox,"Choose one");
        objectsToHighlight.put(usernameTextfieldVBox,"Empty field!");
    }


    private void setDiffificultyTogglesListener() {
        // When radioButton is selected
        difficultyToggles.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
            // Set chosen option
            difficultyChosen = Short.valueOf(
                    ((RadioButton)difficultyToggles
                            .getSelectedToggle())
                            .getId()
            );
            // Remove the group from nodes which will get highlighted if user is trying to start game w/o selecting one of its RadioButtons
            objectsToHighlight.remove(difficultyTogglesVBox);
        });
    }
    private void setTypeOfGameTogglesListener() {
        // When radioButton is selected
        typeOfGameToggles.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
            // Set chosen option
            typeOfGameChosen = Short.valueOf(
                    ((RadioButton)typeOfGameToggles
                            .getSelectedToggle())
                            .getId()
            );
            // Remove the group from nodes which will get highlighted if user is trying to start game w/o selecting one of its RadioButtons
            objectsToHighlight.remove(typeOfGameTogglesVBox);
            // Set Word class static field which defines what language will the given word required to be translated to
            Word.setTypeOfGame(typeOfGameChosen);
        });
    }
    private void setPartOfSpeechToggleListener() {
        // When ToggleButton is selected
        partOfSpeechChosen = false;
        partOfSpeechToggle.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Set chosen option
            partOfSpeechChosen = partOfSpeechToggle.isSelected();
            // No need in removing the node from objectsToHighlight, because it is initially not in the list (the node cannot be unselected)
        });
    }
    private void setUsernameTextFieldListener() {
        // When username typed in
        username.textProperty().addListener((observable, oldValue, newValue)->{
            // If such username is available (is not taken yet), then save the username into variable and remove the node from objectsToHighlight
            if (User.checkAvailability(username.getText())) {
                usernameChosen = username.getText();
                objectsToHighlight.remove(usernameTextfieldVBox);
            }else { // Other way set variable that saves selected username as null and put the node (its parent - VBox) into objectsToHighlight again with appropriate message
                usernameChosen = null;
                // If it is empty - "Empty field!"; else - "This username is already taken!"
                objectsToHighlight.put(usernameTextfieldVBox,(username.getText().equals("") ? "Empty field!" : "This username is already taken!"));
            }
        });
    }

    //When toggleButton is pressed, it changes its label
    public void changeText(ActionEvent actionEvent) {
        var button = (ToggleButton)actionEvent.getSource();
        button.setText((button.getText().equals("Yes") ? "No" : "Yes"));
    }

    //Button Back
    public void goToLobby(ActionEvent actionEvent) {
        GameLobby.loadScene();
    }

    //Button Play
    public void launchGame(ActionEvent actionEvent) {
        // If any of nodes have been highlighted (something isnt selected yet), then program will not start the game
        if (!highlight(objectsToHighlight)) {
            GameSetup.launchGame();
        }
    }
    private boolean highlight(HashMap<VBox,String> objectsToChoose) {
        boolean result = false;
        // Notification like "choose one of the options"
        AlertLabel alertLabel;
        // Position relatively to scene for alertLabel
        Bounds boundsInScene;
        // For each VBox that contains unselected toggles/textfield
        for (VBox box : objectsToChoose.keySet()) {
            // if any VBoxes in the list, than user hasn't chosen something yet, thus the game may not be started which is defined by result variable
            result = true;
            //objectsToChoose.get(box) - appropriate message for certain vbox
            alertLabel = new AlertLabel(objectsToChoose.get(box));
            alertLabel.setPositionRelatively(box.getChildren().get(0));

            // Add label to scene
            rootAnchorPane.getChildren().add(alertLabel);


            addListenersToRemoveAlert(alertLabel, box);

        }
        return result;
    }

    private void addListenersToRemoveAlert(AlertLabel alertLabel, VBox box) {
        // Event - removing alert
        var e = new EventHandler<Event>(){
            @Override
            public void handle(Event event) {
                alertLabel.disappear(rootAnchorPane.getChildren());
            }
        };
        // Add event to situation, when window gets resized
        Main.stage.widthProperty().addListener((observable, oldValue, newValue)->{
            e.handle(new ActionEvent());
        });
        Main.stage.heightProperty().addListener((observable, oldValue, newValue)->{
            e.handle(new ActionEvent());
        });

        // Add event to situation, when one of the VBox children gets clicked on
        box.getChildren().forEach(i -> {
            i.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
        });

        // When play button gets clicked on (prevent from multiple alert notification appearing)
        playButton.addEventHandler(ActionEvent.ACTION, e);
    }
}
