package language.learn.quiz.user;

import language.learn.quiz.Additional;
import language.learn.quiz.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.word.Word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class User {
    // Get user name
    public static String getName() {
        System.out.print("Input your name: ");
        return Additional.getString();
    }
    private static String clean(String answer) {
        return answer;
//                .replaceAll("\\s+","")
//                .strip();
    }


    public static boolean isCorrectAnswer(Word word, String givenAnswer) {
        try {
            // Get answer to word. Return whether it's correct or not
            // TODO problem with russian
            givenAnswer = clean(givenAnswer);
            if (givenAnswer.length()==0) return false;
            return anyMatches(givenAnswer,word.translation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean isCorrectPart(Word word, String givenType) {
        // Get answer to word type. Return whether it's correct or not
        givenType = clean(givenType);

        if (givenType.length()==0) return false;
//           System.out.println("Word type: "+word.type+"; is it verb? : "+word.type.contains("verb"));
//           System.out.println("Word type: "+word.type+"; is it noun? : "+word.type.contains("noun"));
//           System.out.println("Word type: "+word.type+"; is it misc? : "+word.type.contains("misc"));
//           System.out.println("Word type: "+word.type+"; is it adjective? : "+word.type.contains("adjective"));
        String finalGivenType = givenType;
        return Stream.of(word.type.split(",")).anyMatch(i -> i.strip().equals(finalGivenType));
//        return word.type.contains(type);
    }

    private static boolean anyMatches(String answer, String toMatchTO) {
        return Stream.of(answer.split(",")).anyMatch(i ->
            Arrays.stream(toMatchTO.split(",")).anyMatch(j -> clean(j).matches(clean(i))));
    }



    public static List<String> takenUsernames = CSVFileWithRecords.getUsernames();
    public static boolean checkAvailability(String text) {
        if (takenUsernames == null) {
            takenUsernames = CSVFileWithRecords.getUsernames();
        }
        return ((!text.equals("")) && (!takenUsernames.contains(text)));
    }
}
