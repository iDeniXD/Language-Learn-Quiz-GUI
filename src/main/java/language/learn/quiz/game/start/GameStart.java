package language.learn.quiz.game.start;

import language.learn.quiz.game.CSV.CSVFileWithWords.CSVFileWithWords;
import language.learn.quiz.game.languages.Languages;
import language.learn.quiz.game.typeOfGame.ENG_RUS_mixed;
import language.learn.quiz.game.user.User;
import language.learn.quiz.game.word.Word;

public class GameStart {
    public static int throwWords(int numberOfWords, int typeOfGame, boolean usingPartsOfSpeech) {
        // word - random word from CSV file
        Word word = new Word();
        //result - points, etc.
        int points = 0;

        // translation - if has been translated correctly
        boolean translation;
        // type - if type has been named correctly
        boolean type;

        // numberOfWords - difficulty
        System.out.println("multiple words in translation divide by comma");
        for (int i = 0; i < numberOfWords; i++) {
            // Get random word from CSV file
            word = CSVFileWithWords.getWord(typeOfGame);
            // Ask user the translation and type(if needed). True if correct
            System.out.println("Points: "+points);
            System.out.printf("Word number %d%n",i+1);
            translation = User.isCorrectAnswer(word);
            // If correct point++
            if (translation) {
                if (usingPartsOfSpeech) {
                    type = User.isCorrectType(word);
                } else {
                    type = true;
                }
                if (type) {
                    System.out.println("Correct! You achieved a point! \n");
                    points++;
                } else {
                    System.out.printf("Wrong part of speech! It was a %s. Your answer: %s. Your cleaned answer: %s%n%n",word.type,User.typeAnswer,User.wordAnswerCleaned);
                }
            } else {
                System.out.printf("Wrong word! The word was %s. It is %s. Your answer: %s. Your cleaned answer: %s%n%n",word.translation,word.type,User.wordAnswer,User.wordAnswerCleaned);
            }
        }
        return points;
    }

    private static String getLagnguageToTranslateFrom(int typeOfGame) {
        return ENG_RUS_mixed.languageFrom(typeOfGame);
    }

    private static String getLagnguageToTranslateTo(String langFrom) {
        if (langFrom.equals(Languages.RUS())) return Languages.ENG();
        else return Languages.RUS();
    }
}
