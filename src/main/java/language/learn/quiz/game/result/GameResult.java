package language.learn.quiz.game.result;

import language.learn.quiz.game.CSV.CSVFileWithRecords.CSVFileWithRecords;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameResult {
    public int points;
    public int numberOfWords;
    public int typeOfGame;
    public boolean usingPartsOfSpeech;
    public String name;

    public void show() {
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

    public void record() {
        String[] record = prepareRecord(numberOfWords,typeOfGame,usingPartsOfSpeech,name);
        CSVFileWithRecords.makeRecord(record);
    }

    private String[] prepareRecord(int numberOfWords, int typeOfGame, boolean usingPartsOfSpeech, String name) {
        SimpleDateFormat formatter= new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return new String[]{name,String.valueOf(numberOfWords), String.valueOf(typeOfGame), String.valueOf(usingPartsOfSpeech), String.valueOf(points),formatter.format(date)};
    }
}
