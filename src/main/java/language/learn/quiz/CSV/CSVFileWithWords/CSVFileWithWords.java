package language.learn.quiz.CSV.CSVFileWithWords;

import com.opencsv.CSVReader;
import language.learn.quiz.Main;
import language.learn.quiz.Additional;
import language.learn.quiz.word.Word;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

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
        Random rnd = new Random();
        Word word;
        String[] wordLine = words.get(rnd.nextInt(words.size()));
        word = new Word(wordLine);

        word.setup();

        return word;
    }

    private static boolean acceptable(Word word) {
        return  (false
//                || !word.translation.contains("to understand")
//                || !word.translation.contains("to cry, shout")
//                || !word.translation.contains("now and then")
//                || !word.translation.contains("to come, arrive")
//                || !word.translation.contains("#")
//                || !word.translation.contains("to, towards, by")
//                || !word.translation.contains("bride (See #288)")
//                || !word.translation.contains("she wears black")
//                || !word.translation.contains("(")
//                || !word.translation.contains("to pass, go;study")
//                || !word.translation.contains(";")
//                || !word.translation.contains("somebody else")
//                || !word.translation.contains("thin, skinny; masterrussian")
//                || !word.translation.contains("which (old-fashioned")
//                || !word.translation.contains("com")
//                || !word.translation.contains("parent; masterrussian")
//                || !word.translation.contains("to come up, approach;to fit")
//                || !word.translation.contains("Mr.")
//                || !word.translation.contains("dot")
//                || !word.translation.contains("masterrussian")
//                || !word.translation.contains("only, as soon as")
        );
    }

}
