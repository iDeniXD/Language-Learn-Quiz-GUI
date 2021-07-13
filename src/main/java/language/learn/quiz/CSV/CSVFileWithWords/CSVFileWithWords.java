package language.learn.quiz.CSV.CSVFileWithWords;

import com.opencsv.CSVReader;
import language.learn.quiz.Main;
import language.learn.quiz.Additional;
import language.learn.quiz.word.Word;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVFileWithWords {

    static String fileName = Main.class.getResource("CSVFileWithWords/words.csv").getFile();
    private static List<String[]> lines;
    public static Word getWord(int typeOfGame) {
        // Get all file lines
        if (lines == null) {
            lines = readAllLines();
        }
        // using these lines choose random word
        Word rndword;
        do {
            rndword = getRandomWord(lines);
        } while( false // TODO clean word receiving test
//                || !rndword.translation.contains("now and then")
//                || !rndword.translation.contains("to come, arrive")
//                || !rndword.translation.contains("#")
//                || !rndword.translation.contains("to, towards, by")
//                || !rndword.translation.contains("bride (See #288)")
//                || !rndword.translation.contains("she wears black")
//                || !rndword.translation.contains("(")
//                || !rndword.translation.contains("to pass, go;study")
//                || !rndword.translation.contains(";")
//                || !rndword.translation.contains("somebody else")
//                || !rndword.translation.contains("thin, skinny; masterrussian")
//                || !rndword.translation.contains("which (old-fashioned")
//                || !rndword.translation.contains("com")
//                || !rndword.translation.contains("parent; masterrussian")
//                || !rndword.translation.contains("to come up, approach;to fit")
//                || !rndword.translation.contains("Mr.")
//                || !rndword.translation.contains("dot")
//                || !rndword.translation.contains("masterrussian")
//                || !rndword.translation.contains("only, as soon as")
        );
//        ISO-8859-1
        Word word = rndword;//getRandomWord(lines);
        // if translation language is russian, switch langOrigin and langToTranslate.
        // because langOrigin is String with value of the word in English (default)
        // and langToTranslate is String with value of the word in Russian, so that
        // we need to switch them since we are playing in ENG -> RUS

        // if both simply choose randomly whether to switch or not
        //if (langTo.equals(Languages.RUS())) {

        word.setTranslateTo(typeOfGame-1);
        word.clean();

        // TODO problem with russian
        return word;
    }

    private static Word getRandomWord(List<String[]> lines) {
        // Receiving random word
        var line = lines.get(Additional.rnd.nextInt(lines.size()));
        // creating word settings
        Word word = new Word();
        word.original = line[0];
        word.translation = line[1];
        word.type = line[2];

        return word;
    }

    private static List<String[]> readAllLines() {
        // Open file. If success - read lines from it
        try {
//            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8));
            FileReader fileReader = new FileReader(fileName, StandardCharsets.UTF_8);
            return readAllLines(fileReader);
        } catch (IOException e){
            throw new RuntimeException("Could not find file", e);
        }
    }
    private static List<String[]> readAllLines(FileReader reader) throws IOException {
        // Read all lines from file
        CSVReader csvreader = new CSVReader(reader);
        List<String[]> list = csvreader.readAll();
        reader.close();
        csvreader.close();
        return list;
    }

}
