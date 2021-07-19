package language.learn.quiz.user;

import language.learn.quiz.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.word.Word;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class User {
    private static String clean(String answer) {
        return answer
                .replaceAll("\\s+"," ") // multiple spaces into one
                .replaceAll("to ","") // preposition to remove. BE CAUTIOUS! If there is any words that end on "to ", then they have to be treated differently
                .strip()
        ;
    }


    public static boolean isCorrectTranslation(Word word, String givenAnswer) {
        try {
            givenAnswer = clean(givenAnswer);

            if (givenAnswer.length()==0) return false; // empty
            return anyMatches(givenAnswer,word.translation); // if such answer is among the correct answers of the word, then true
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean isCorrectPart(Word word, String givenType) {
        givenType = clean(givenType);

        if (givenType.length()==0) return false; // empty
        String finalGivenType = givenType;
        return Stream.of(word.partOfSpeech.split(",")).anyMatch(i -> i.strip().equals(finalGivenType));
    }

    private static boolean anyMatches(String answer, String toMatchTO) {
        // As user might give multiple answers (separated by comma), then each of them has to be compared to each answer (also separated by comma)
        return Stream.of(answer.split(",")).anyMatch(i ->
            Arrays.stream(toMatchTO.split(",")).anyMatch(j -> clean(j).matches(clean(i))));
    }



    public static List<String> takenUsernames = CSVFileWithRecords.getUsernames();
    public static boolean checkAvailability(String text) {
        if (takenUsernames == null) { // get taken usernames
            takenUsernames = CSVFileWithRecords.getUsernames();
        }
        // return if chosen username is already taken
        return ((!text.equals("")) && (!takenUsernames.contains(text)));
    }
}
