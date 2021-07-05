package language.learn.quiz.game.typeOfGame;

import javafx.scene.control.Toggle;
import language.learn.quiz.game.Additional;
import language.learn.quiz.game.languages.Languages;

import java.util.LinkedHashMap;

public class ENG_RUS_mixed {
    public static int getType() {
        var types = getTypes();
        System.out.println("Choose type of game:");
        showTypes(types);
        return Additional.getNumber();
    }
    public static String getType(int index) {
        var types = getTypes();
        return types.get(index);
    }

    private static void showTypes(LinkedHashMap<Integer, String> types) {
        for (int i :
                types.keySet()) {
            System.out.printf("%d. %s%n",i,types.get(i));
        }
    }

    public static LinkedHashMap<Integer, String> getTypes() {
        LinkedHashMap<Integer, String> types = new LinkedHashMap<>();
        types.put(1,"%s -> %s".formatted(Languages.ENG(),Languages.RUS()));
        types.put(2,"%s -> %s".formatted(Languages.RUS(),Languages.ENG()));
        types.put(3,"%s <-> %s".formatted(Languages.ENG(),Languages.RUS()));
        return types;
    }
    public static String languageTo(int index) {
        LinkedHashMap<Integer, String> lang = new LinkedHashMap<>();
        lang.put(1,Languages.RUS());
        lang.put(2,Languages.ENG());
        lang.put(3,"Mixed");
        if (index == 3) {
            index = Additional.rnd.nextInt(2)+1;
            System.out.println(index);
//            for (int i = 0; i < 10; i++) {
//                System.out.println(rnd.nextInt(2)+1);
//            }
            return lang.get(Additional.rnd.nextInt(2)+1);
        }
        return lang.get(index);
    }

    public static String languageFrom(int index) {
        LinkedHashMap<Integer, String> lang = new LinkedHashMap<>();
        lang.put(1,Languages.ENG());
        lang.put(2,Languages.RUS());
        lang.put(3,"Mixed");
        if (index == 3) {
            return lang.get(Additional.rnd.nextInt(2)+1);
        }
        return lang.get(index);
    }

    public static short getTypeIndex(String text) {
        var types = getTypes();
        for (int i = 0; i < types.size(); i++) {
            if(text.contains(String.valueOf(types.values().stream().toList().get(i)))){
                return types.keySet().stream().toList().get(i).shortValue();
            }
        }
        return -1;
    }
}
