package language.learn.quiz.CSV.CSVFileWithWords;

import com.opencsv.CSVReader;
import language.learn.quiz.Additional;
import language.learn.quiz.Main;
import language.learn.quiz.word.Word;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVFileWithWords {

    static String fileName = Main.class.getResource("CSVFileWithWords/words.csv").getFile();
    static List<String[]> words;
    public static Word getWord(){
        words = getWords();
        return chooseRandom(words);
    }

    private static List<String[]> getWords() {
        if (words == null) {
            words = readCSVFile();
        }
        return words;
    }

    private static List<String[]> readCSVFile() {
        try {
            CSVReader csvreader = new CSVReader(new FileReader(fileName, StandardCharsets.UTF_8));
            return csvreader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Word chooseRandom(List<String[]> words) {
        Word word;
        String[] wordLine = words.get(Additional.rnd.nextInt(words.size()));
        word = new Word(wordLine);

        word.setup();

        return word;
    }

}
