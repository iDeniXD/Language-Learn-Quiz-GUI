package language.learn.quiz.game.CSV.CSVFileWithRecords;

import com.opencsv.CSVWriter;
import language.learn.quiz.Main;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileWithRecordsTest {
//    static String fileName = ClassLoader.getSystemClassLoader().getResource("records.csv").getFile();
    @Test
    void makeRecord() throws IOException {
        System.out.println(ClassLoader.getSystemClassLoader().getResource(""));
//        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName, true))) {
//            writer.writeNext(new String[]{"hey :D", "hey :D", "hey :D", "hey :D", "hey :D", "hey :D"});
//        } catch (IOException e) {
//            throw new RuntimeException("Could not find file", e);
//        }
    }
}