package language.learn.quiz.CSV.CSVFileWithRecords;

import language.learn.quiz.Main;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CSVFileWithRecordsTest {
//    static String fileName = ClassLoader.getSystemClassLoader().getResource("records.csv").getFile();
    @Test
    void makeRecord() throws IOException {

        System.out.println(ClassLoader.getSystemClassLoader().getResource("CSVFileWithRecords/records.csv"));
    }
}