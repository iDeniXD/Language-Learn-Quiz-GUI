package language.learn.quiz.game.result;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import language.learn.quiz.Main;
import language.learn.quiz.game.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.game.typeOfGame.ENG_RUS_mixed;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameResult {
    public int points;
    public int numberOfWords;
    public int typeOfGame;
    public boolean usingPartsOfSpeech;
    public String name;
    public String date;
    private static GameResult result;

    static Parent root = null;
    public void show() {
        result = this;
        try {
            root = getRoot();
            show(root);
            // Scene shows up, then FXMLLoader calls method initialize(), which continues program
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();


        System.out.printf("You achieved %s points.%n",points);
        if(points / (double)numberOfWords < 0.6) {
            System.out.println("Practise your English more!");
        } else if (points / (double)numberOfWords == 0.6) {
            System.out.println("Not bad, but you could do better!");
        } else {
            System.out.println("Great!");
        }
        System.out.println("");
    }

    private void show(Parent root) {
        Main.stage.getScene().setRoot(root);
    }

    private Parent getRoot() throws IOException {
        root = FXMLLoader.load(Main.class.getResource("result/GameResult.fxml"));
        return root;
    }
    @FXML
    public void initialize() {
        setup();
    }
    @FXML
    Label nameLabel, numberOfWordsGivenLabel, pointsLabel, usingPartsOfSpeechLabel, dateLabel;

    private void setup() {
        setupLabels();
    }

    private void setupLabels() {
        nameLabel.setText(result.name);
        numberOfWordsGivenLabel.setText(String.valueOf(result.numberOfWords));
        pointsLabel.setText(String.valueOf(result.points));
        usingPartsOfSpeechLabel.setText(String.valueOf(result.usingPartsOfSpeech));
        dateLabel.setText(result.date);
    }

    public void record() {
        String[] record = prepareRecord(numberOfWords,typeOfGame,usingPartsOfSpeech,name);
        CSVFileWithRecords.makeRecord(record);
    }

    private String[] prepareRecord(int numberOfWords, int typeOfGame, boolean usingPartsOfSpeech, String name) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        this.date = formatter.format(date);
        return new String[]{name,String.valueOf(numberOfWords), String.valueOf(ENG_RUS_mixed.getType(typeOfGame)), String.valueOf(usingPartsOfSpeech), String.valueOf(points),this.date};
    }

    public void goToMainMenu(ActionEvent actionEvent) {
    }
}
