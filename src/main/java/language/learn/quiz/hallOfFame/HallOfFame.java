package language.learn.quiz.hallOfFame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import language.learn.quiz.Main;
import language.learn.quiz.game.CSV.CSVFileWithRecords.CSVFileWithRecords;

import java.io.IOException;
import java.util.List;


public class HallOfFame {

    static Scene hallOfFameScene = null;

    public static void show() {
        try {
            showScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showScene() throws IOException {
        if (hallOfFameScene == null){
            Parent FXMLFile = FXMLLoader.load(HallOfFame.class.getResource("HallOfFame.fxml"));
            hallOfFameScene = new Scene(FXMLFile);
            hallOfFameScene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
            loadRecords();
        }
        Main.currentStage.setScene(hallOfFameScene);
    }
    private static void loadRecords() {
        GridPane gp = createGridPane();
        var records = getRecords();
        addRecordsToGridPane(gp,records);
    }

    private static void addRecordsToGridPane(GridPane gp, List<String[]> records) {
        for (int i = 0; i < records.size(); i++) {
            addRecordToGridPane(gp,i,records.get(i));
        }
        for (int i = 0; i < records.get(0).length; i++) {
            gp.getChildren().get(i+1).setStyle("-fx-font-weight: bold");
        }
        SetColumnConstraints(gp,records.get(0).length);
    }

    private static void SetColumnConstraints(GridPane gp, int length) {
        ColumnConstraints col;
        for (int i = 0; i < length; i++) {
            col = new ColumnConstraints();
            col.setHgrow(Priority.ALWAYS);
            gp.getColumnConstraints().add(col);
        }
        gp.getColumnConstraints().get(0).setPercentWidth(10);
    }

    private static void addRecordToGridPane(GridPane gp, int i, String[] strings) {
        Label recordLabel;
        for (int j = 0; j < strings.length; j++) {
            recordLabel = new Label(strings[j]);
            recordLabel.setWrapText(true);
            GridPane.setHalignment(recordLabel, HPos.CENTER);
            gp.add(recordLabel,j,i);
        }
    }

    private static GridPane createGridPane() {
        ScrollPane sp = (ScrollPane)hallOfFameScene.lookup("#ScrollPaneWithRecords");

        GridPane gp = new GridPane();
        gp.setGridLinesVisible(true);

        sp.setContent(gp);

        return gp;
    }
    private static List<String[]> getRecords() {
        var list = CSVFileWithRecords.getRecords();
        return list;

//        List<String[]> list = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(new String[]{"a","b","c","d","e"});
//        }
//        return list;
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        Main.currentStage.setScene(Main.menuScene);
    }
}
