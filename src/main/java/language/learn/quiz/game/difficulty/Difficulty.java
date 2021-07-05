package language.learn.quiz.game.difficulty;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import language.learn.quiz.game.Additional;

import java.util.LinkedHashMap;

public class Difficulty {
    public static int getNumberOfWords() {
        var words = getWords();
        System.out.println("Choose difficulty:");
        showWords(words);
        return words.get(Additional.getNumber());
    }

    private static void showWords(LinkedHashMap<Integer, Integer> words) {
        for (int i :
                words.keySet()) {
            System.out.printf("%d. %d Words%n",i,words.get(i));
        }
    }

    private static LinkedHashMap<Integer, Integer> getWords() {
        LinkedHashMap<Integer, Integer> words = new LinkedHashMap<>();
        words.put(1,10);
        words.put(2,15);
        words.put(3,20);
        return words;
    }

    public static int getNumberOfWords(Toggle selectedToggle) {
        return Integer.parseInt(((RadioButton)selectedToggle).getText().replaceAll("[^0-9]",""));
    }

    public static String getDifficulty(int parseInt) {
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
