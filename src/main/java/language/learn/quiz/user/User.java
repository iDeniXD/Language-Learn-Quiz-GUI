package language.learn.quiz.user;

import language.learn.quiz.Additional;
import language.learn.quiz.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.word.Word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class User {
    private static String clean(String answer) {
        return answer
                .replaceAll("\\s+"," ")
                .replaceAll("to ","")
                .strip()
        ;
    }


    public static boolean isCorrectTranslation(Word word, String givenAnswer) {
        try {
            // Get answer to word. Return whether it's correct or not
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
        String finalGivenType = givenType;
        return Stream.of(word.partOfSpeech.split(",")).anyMatch(i -> i.strip().equals(finalGivenType));
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
