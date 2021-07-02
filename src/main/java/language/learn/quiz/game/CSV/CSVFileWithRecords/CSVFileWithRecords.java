package language.learn.quiz.game.CSV.CSVFileWithRecords;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import language.learn.quiz.Main;
import language.learn.quiz.game.typeOfGame.ENG_RUS_mixed;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CSVFileWithRecords {
//    static String fileName = Objects.requireNonNull(CSVFileWithRecords.class.getClassLoader().getResource("records.csv")).getFile();
    static String fileName = CSVFileWithRecords.class.getClassLoader().getResource("language/learn/quiz/CSVFileWithRecords/records.csv").getFile();
//    static String fileName = ClassLoader.getSystemClassLoader().getResource("records.csv").getFile();
    static String[] header = new String[]{"User","Number of Words Given","Type of Game","Using Parts of Speech","Achieved Points","Time of the Record"};
    //User,Number of Words Given,Type of Game,Using Parts of Speech,Achieved Points,Time of the Record
    public static void makeRecord(String[] record) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            CSVWriter csvwriter = new CSVWriter(fileWriter);
            if (!exists(fileName)){
//                fileWriter.write(String.valueOf(header));
                csvwriter.writeNext(header);
            }
//            fileWriter.write(String.valueOf(record));
            csvwriter.writeNext(record);
            System.out.println(Arrays.toString(record));
        } catch (IOException e){
            throw new RuntimeException("Could not find file", e);
        }
    }


    private static boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
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
            if (list.get(0)[i].contains("Using Parts of Speech")){
                for (int j = 1; j < list.size(); j++) {
                    list.get(j)[i] = (list.get(j)[i].contains("true") ? "Yes" : "No");
                }
            }
            // Replacing 1/2/3 in Type of Game to ENG->RUS, RUS->ENG, MIXED
            if (list.get(0)[i].contains("Type of Game")){
                for (int j = 1; j < list.size(); j++) {
                    list.get(j)[i] = ENG_RUS_mixed.getType(Integer.parseInt(list.get(j)[i].strip()));
                }
            }
        }

        return list;
    }

    public static List<String[]> getRecords() {
        try {
            FileReader fileReader = new FileReader(fileName);
            return readAllLines(fileReader);
        } catch (IOException e){
            throw new RuntimeException("Could not find file", e);
        }
    }
}
