package language.learn.quiz.CSV.CSVFileWithRecords;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWithRecords {
    static String fileName = "src/main/resources/language/learn/quiz/CSVFileWithRecords/records.csv";
    static String[] header = new String[]{"User","Number of Words Given","Type of Game","Using Parts of Speech","Achieved Points","Time of the Record"};
    //User,Number of Words Given,Type of Game,Using Parts of Speech,Achieved Points,Time of the Record
    public static void makeRecord(String[] record) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName, true));
            writer.writeNext(record);
            writer.close();
        } catch (IOException e){
            throw new RuntimeException("Could not find file", e);
        }
    }

    public static List<String[]> getRecords() {
        try {
            CSVReader csvreader = new CSVReader(new FileReader(fileName));
            return csvreader.readAll();
        } catch (IOException e){
            throw new RuntimeException("Could not find file", e);
        }
    }

    public static List<String> getUsernames() {
        // When game is being setup, user chooses a username and usernames cannot repeat,
        // so program needs to know what usernames are already taken
        List<String[]> records = getRecords();
        List<String> usernames = new ArrayList<>();
        records.forEach(i -> usernames.add(i[0]));
        return usernames;
    }
}
