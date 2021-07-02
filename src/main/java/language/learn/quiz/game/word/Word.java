package language.learn.quiz.game.word;

import language.learn.quiz.game.languages.Languages;

public class Word {
    public String type;
    public String original;
    public String translation;
    private String clarification;
    private String translateTo;

    public String getClarification() {
        return clarification;
    }

    public void setClarification(String clarification) {
        this.clarification = clarification;
    }
    public void setClarification() {
        translation = cleanThisField(translation);
        if (translation.contains(";")) {
            this.clarification = this.translation.substring(this.translation.indexOf(";")+1);
            this.translation = this.translation.substring(0,this.translation.indexOf(";"));
        } else {
            this.clarification = "";
        }
        if (translation.contains("(")) {
            this.clarification =  this.translation.substring(this.translation.indexOf("(")+1,this.translation.indexOf(")")) + ((!this.clarification.equals("")) ? (". " + this.clarification) : "");
            translation = translation.replaceAll("\\(.*?\\)","");

        }
        //Пропустить clarification через masterrussin, dot и com
    }


    public String getTranslateTo() {
        return translateTo;
    }

    public void setTranslateTo(String translateTo) {
        if (!translateTo.equals(this.translateTo)) {
            switchTranslations();
        }
        this.translateTo = translateTo;
    }
    public void setTranslateTo(int typeOfGame) {
        setClarification();
        var language = Languages.getLanguage(typeOfGame);
        this.translateTo = language;
        if (language.equals("Mixed")) {
            language = Languages.Mixed();
        }
        if (language.equals(Languages.RUS())) {
            switchTranslations();
        }
            this.translateTo = language;
    }

    private void switchTranslations() {
        var tmp = original;
        original = translation;
        translation = tmp;
    }

    public void clean() {
        this.original = cleanThisField(this.original);
        this.translation = cleanThisField(this.translation);
        this.type = cleanThisField(this.type);
        this.clarification = cleanThisField(this.clarification);
    }

    private static String[] wordsPattern = new String[]{"masterrussian","dot, com","dot com","com"};
    private static String[] notePattern = new String[]{"\\(See #(\\d)+\\)","\\(see #(\\d)+\\)","see #(\\d)","See #(\\d)"};
    private String cleanThisField(String field) {
//        field = " "+field+" ";
//        for (var p:
//                wordsPattern) {
//            var indexOfPattern = field.indexOf(p);
//            if (indexOfPattern != -1) {
//                if (!(String.valueOf(field.charAt(indexOfPattern+p.length())).matches("[\\p{IsAlphabetic}]") ||
//                    String.valueOf(field.charAt(indexOfPattern-1)).matches("[\\p{IsAlphabetic}]"))) {
//                    field = field.replaceAll(p, "");
//                }
//            }
//        }
//        for (var p:
//                notePattern) {
//            field = field.replaceAll(p,"");
//        }
        for (String p :
                wordsPattern) {
            if (!(field.matches(".*%s[\\p{IsAlphabetic}].*".formatted(p)) || field.matches(".*%s[\\p{IsAlphabetic}].*".formatted(p)))) {
                field = field.replaceAll(p, "");
            }
        }
//        for (String p :
//                wordsPattern) {
//            field = field.replaceAll(p,"");
//        }
        for (String p:
                notePattern) {
            field = field.replaceAll(p, "");
        }
        while (field.matches(".*[^\\p{IsAlphabetic}.\")]$")) {
            field = field.substring(0,field.length()-1);
        }
        while (field.matches("^[^\\p{IsAlphabetic}.\"'(].*")) {
            field = field.substring(1);
        }
        if(!field.matches(".*[\\p{IsAlphabetic}].*")){
            field = "";
        }
        return field
                .replaceAll("[.+]",".")
                .replaceAll("[,+]",",")
                .replaceAll("\\s+"," ")
                .strip();
    }
}
