package language.learn.quiz.game.partOfSpeech;

import language.learn.quiz.game.Additional;

public class PartsOfSpeech {
    public static boolean getUsingPartsOfSpeech() {
        System.out.println("Do you want to type part of speech of the word given? (Y/N)");
        return Additional.getBool();
    }
}
