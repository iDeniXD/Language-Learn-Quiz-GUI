package language.learn.quiz;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Additional {
    public static Scanner input = new Scanner(System.in);
    public static Random rnd = new Random();

    public static String getString() {
        return input.nextLine();
    }

    public static int getNumber() {
        return Integer.parseInt(input.nextLine());
    }

    public static boolean getBool() {
        var answer = getString().toLowerCase(Locale.ROOT).charAt(0);
        if (answer == 'y') {
            return true;
        } else if (answer == 'n') {
            return false;
        } else {
            throw new IllegalArgumentException("Type Y or N to choose");
        }
    }
}
