package language.learn.quiz.state;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import language.learn.quiz.Main;
import language.learn.quiz.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.hallOfFame.HallOfFame;
import language.learn.quiz.languages.Languages;
import language.learn.quiz.user.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GameState {
    public int points;
    public int numberOfWords;
    public int typeOfGame;
    public boolean usingPartsOfSpeech;
    public String name;
    public String date;
    private static GameState result;

    static Parent root = null;

    public void record() {
        String[] record = prepareRecord(numberOfWords,typeOfGame,usingPartsOfSpeech,name);
        CSVFileWithRecords.makeRecord(record);
        // In order to update records in case user decides to start new game
        User.takenUsernames = null;
        HallOfFame.resetScene();
    }

    private String[] prepareRecord(int numberOfWords, int typeOfGame, boolean usingPartsOfSpeech, String name) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        this.date = formatter.format(date);
        return new String[]{name,String.valueOf(numberOfWords), String.valueOf(Languages.getLanguage(typeOfGame)), usingPartsOfSpeech ? "Yes" : "No", String.valueOf(points),this.date};
    }
}
