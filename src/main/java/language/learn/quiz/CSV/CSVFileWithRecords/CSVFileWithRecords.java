package language.learn.quiz.CSV.CSVFileWithRecords;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import language.learn.quiz.Main;
import language.learn.quiz.hallOfFame.HallOfFame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVFileWithRecords {
//    static String fileName = Objects.requireNonNull(CSVFileWithRecords.class.getClassLoader().getResource("records.csv")).getFile();
    static String fileName = "src/main/resources/language/learn/quiz/CSVFileWithRecords/records.csv";
//    static String fileName = ClassLoader.getSystemClassLoader().getResource("records.csv").getFile();
    static String[] header = new String[]{"User","Number of Words Given","Type of Game","Using Parts of Speech","Achieved Points","Time of the Record"};
    //User,Number of Words Given,Type of Game,Using Parts of Speech,Achieved Points,Time of the Record
    public static void makeRecord(String[] record) {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(fileName, true));
            writer.writeNext(record);

            HallOfFame.reload();

            writer.close();
        } catch (IOException e){
            throw new RuntimeException("Could not find file", e);
        }
    }


    private static boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public static List<String[]> getRecords() {
        try {
            FileReader fileReader = new FileReader(fileName);
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

        filter(list);

        return list;
    }

    private static List<String[]> filter(List<String[]> list) {
        for (int i = 0; i < list.get(0).length; i++) {
            // Replacing all trues and falses in Using Parts of Speech to Yes and No
            //TODO try to make this method by using csvreader
            if (list.get(0)[i].contains("Using Parts of Speech")){
                for (int j = 1; j < list.size(); j++) {
                    list.get(j)[i] = (list.get(j)[i].contains("true") ? "Yes" : "No");
                }
            }
        }

        return list;
    }

    public static List<String> getUsernames() {
        List<String[]> records = getRecords();
        List<String> usernames = new ArrayList<>();
        records.forEach(i -> usernames.add(i[0]));
        return usernames;
    }
}
