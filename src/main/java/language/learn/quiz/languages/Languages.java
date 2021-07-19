package language.learn.quiz.languages;

public enum Languages {
    RUS,
    ENG,
    RANDOM;


    private static String first(){
        return String.valueOf(RUS);
    }
    private static String second(){
        return String.valueOf(ENG);
    }
    private static String third(){
        return String.valueOf(RANDOM);
    }
        private static final String[] languages = new String[]{first(),second(),third()};
    public static String getLanguage(int index) {
        return languages[index];
    }


    private static String firstTypeOfGame() {
        return "From "+first()+" to "+second();
    }
    private static String secondTypeOfGame() {
        return "From "+second()+" to "+first();
    }
    private static String thirdTypeOfGame() {
        return third();
    }
        private static final String[] types = new String[]{firstTypeOfGame(),secondTypeOfGame(),thirdTypeOfGame()};
    public static String getTypeOfGameAsTitle(int index){
        return types[index];
    }
}
