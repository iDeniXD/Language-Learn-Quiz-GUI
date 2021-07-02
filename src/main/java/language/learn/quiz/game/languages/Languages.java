package language.learn.quiz.game.languages;

import language.learn.quiz.game.Additional;

public class Languages {
    private static String[] languages = new String[]{"RUS","ENG","Mixed"};
    public static String RUS(){
        return "RUS";
    }
    public static String ENG(){
        return "ENG";
    }
    public static String Mixed() {
        if (Additional.rnd.nextBoolean()) {
            return RUS();
        } else {
            return ENG();
        }
    }
    public static String getLanguage(int index) throws IndexOutOfBoundsException {
        return languages[index];
    }
}
