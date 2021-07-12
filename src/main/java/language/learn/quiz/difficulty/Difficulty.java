package language.learn.quiz.difficulty;

import java.util.LinkedHashMap;

public class Difficulty {

    private static void showWords(LinkedHashMap<Integer, Integer> words) {
        for (int i :
                words.keySet()) {
            System.out.printf("%d. %d Words%n",i,words.get(i));
        }
    }

    public static LinkedHashMap<Integer, Integer> getWords() {
        LinkedHashMap<Integer, Integer> words = new LinkedHashMap<>();
        words.put(1,10);
        words.put(2,15);
        words.put(3,20);
        return words;
    }

    public static String getDifficultyByIndex(int parseInt) {
        return "%d words".formatted(getWords().get(parseInt));
    }

    public static short getDifficultyIndex(String text) {
        var words = getWords();
        for (int i = 0; i < words.size(); i++) {
            if(text.contains(String.valueOf(words.values().stream().toList().get(i)))){
                return words.keySet().stream().toList().get(i).shortValue();
            }
        }
        return -1;
    }
}