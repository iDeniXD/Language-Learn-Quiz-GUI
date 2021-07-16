package language.learn.quiz.word;

import language.learn.quiz.Additional;
import language.learn.quiz.languages.Languages;

public class Word {
    private static short typeOfGame;
    public static void setTypeOfGame(Short typeOfGameChosen) {
        typeOfGame = typeOfGameChosen;
    }
    public static short getTypeOfGame() {
        return typeOfGame;
    }

    public String original;
    public String translation;
    public String partOfSpeech;
    public String clarification;
    public String translateToLanguage;

    public Word(String[] wordLine) {
        // Set fields according to CSV file
        this.original = wordLine[0];
        this.translation = wordLine[1];
        this.partOfSpeech = wordLine[2];
    }
    public void setup(){
        // If there is clarification in translation (typically in brackets or after semicolon), then this needs to get saved into clarification variable
        setClarification();

        // Clean fields from unneeded stuff like CSV file site origin references and other
        cleanFields();

        // If chosen type of game (ENG->RUS or MIXED) requires user to translate to RUS, then fields original and translation need to be switched
        switchFieldsIfNeeded();
    }

    private void setClarification() {
        this.clarification = "";
        // After semicolon
        if (translation.contains(";")) {
            this.clarification = this.translation.substring(this.translation.indexOf(";")+1);
            this.translation = this.translation.substring(0,this.translation.indexOf(";"));
        }
        // In brackets
        if (translation.contains("(")) {
            this.clarification =  this.translation.substring(this.translation.indexOf("(")+1,this.translation.indexOf(")")) + ((this.clarification.equals("")) ? "" : (". " + this.clarification));
            translation = translation.replaceAll("\\(.*?\\)","");
        }
    }

    private void cleanFields() {
        // Clean each field
        original = clean(original);
        translation = clean(translation);
        partOfSpeech = clean(partOfSpeech);
        clarification = clean(clarification);
    }

    private String clean(String field) {
        field = cleanWaterMarks(field);
        field = cleanReferences(field);
        field = cleanSymbols(field);
        return field;
    }

    private String cleanWaterMarks(String field) {
        final String[] marks = new String[]{"masterrussian","dot","com"};
        for (String mark : marks) {
            field = field.replaceAll("\\b%s\\b".formatted(mark),"");
        }
        return field;
    }

    private String cleanReferences(String field) {
        final String[] marks = new String[]{"\\(See #(\\d)+\\)","\\(see #(\\d)+\\)","see #(\\d)","See #(\\d)"};
        for (String mark : marks) {
            field = field.replaceAll(mark, "");
        }
        return field;
    }

    private String cleanSymbols(String field) {
        // If non-alphabetic symbol at the end -> remove it
        while (field.matches(".*[^\\p{IsAlphabetic}.\")]$")) {
            field = field.substring(0,field.length()-1);
        }
        // If non-alphabetic symbol at the beginning -> remove it
        while (field.matches("^[^\\p{IsAlphabetic}\"'(].*")) {
            field = field.substring(1);
        }
        // If no alphabetic symbols found -> make empty string
        if(!field.matches(".*[\\p{IsAlphabetic}].*")){
            field = "";
        }
        // Remove possible multiple symbols
        field = field
                .replaceAll("[.+]",".")
                .replaceAll("[,+]",",")
                .replaceAll("\\s+"," ")
                .strip();
        return field;
    }

    private void switchFieldsIfNeeded() {
        translateToLanguage = Languages.getLanguage(1);
        if ((typeOfGame == 1) || (typeOfGame == 2 && Additional.rnd.nextBoolean())) {
            translateToLanguage = Languages.getLanguage(0);
            switchFields();
        } else {
            this.clarification = "";
        }
    }
    private void switchFields() {
        var tmp = this.original;
        this.original = this.translation;
        this.translation = tmp;
    }
}
