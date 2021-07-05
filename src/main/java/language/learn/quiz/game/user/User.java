package language.learn.quiz.game.user;

import language.learn.quiz.game.Additional;
import language.learn.quiz.game.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.game.languages.Languages;
import language.learn.quiz.game.word.Word;

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
        return answer
//                .replaceAll("\\s+"," ")
                .strip();
    }

    private static String givenAnswer;
    public static String getAnswer(){
//        var s = "а1б2в3г4д5е6ё7ж8з9и0й/к*л-м+н.о,п/р?с\\т|у'ф\"х:ц;ч[ш]щ{ъ}ы=ь_э!ю@я#a$b%c^d&e9f8g7h6i5j4k2l]m[n}o{p)q(r*s&t^u%v$w#x@y!z".replaceAll("[^\\p{IsAlphabetic}]", "");
//        System.out.println(s);
        String answer = clean(Additional.getString());
        givenAnswer = answer;
        return answer;
    }


    public static String wordAnswer;
    public static String wordAnswerCleaned;
    public static boolean isCorrectAnswer(Word word) {
        try {
            // Get answer to word. Return whether it's correct or not
            System.out.printf("Print the word \"%s\"%s in %s: ",word.original,(word.getTranslateTo().equals(Languages.RUS()) && !word.getClarification().equals("")) ? " (%s)".formatted(word.getClarification()) : "", word.getTranslateTo());
            var answer = getAnswer();
            wordAnswer = givenAnswer;
            if (answer.length()==0) return false;
            return anyMatches(answer,word.translation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static String typeAnswer;
    public static boolean isCorrectType(Word word) {
        // Get answer to word type. Return whether it's correct or not
        System.out.printf("Print the type of word \"%s\": ",word.original);
        var type = getAnswer();
        typeAnswer = givenAnswer;

        if (type.length()==0) return false;
//           System.out.println("Word type: "+word.type+"; is it verb? : "+word.type.contains("verb"));
//           System.out.println("Word type: "+word.type+"; is it noun? : "+word.type.contains("noun"));
//           System.out.println("Word type: "+word.type+"; is it misc? : "+word.type.contains("misc"));
//           System.out.println("Word type: "+word.type+"; is it adjective? : "+word.type.contains("adjective"));
        return Stream.of(word.type.split(",")).anyMatch(i -> i.strip().equals(type));
//        return word.type.contains(type);
    }

    private static boolean anyMatches(String answer, String toMatchTO) {
        return Stream.of(answer.split(",")).anyMatch(i ->
            Arrays.stream(toMatchTO.split(",")).anyMatch(j -> clean(j).matches(clean(i))));
    }
    static List<String> takenUsernames = CSVFileWithRecords.getUsernames();
    public static boolean checkAvailability(String text) {
        return ((!text.equals("")) && (!takenUsernames.contains(text)));
    }
}
