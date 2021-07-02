package language.learn.quiz.hallOfFame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import language.learn.quiz.Main;
import language.learn.quiz.game.CSV.CSVFileWithRecords.CSVFileWithRecords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HallOfFame {
    static Scene scene = null;
    public static void show() {
        try {
            scene = getScene();
            show(scene);
            // Scene shows up, then FXMLLoader calls method initialize(), which continues program
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Scene getScene() throws IOException {
        if (scene == null){
            Parent FXMLFile = FXMLLoader.load(HallOfFame.class.getResource("HallOfFame.fxml"));
            scene = new Scene(FXMLFile);
            scene.getStylesheets().add(Main.class.getResource("style.css").toExternalForm());
        }
        return scene;
    }
    private static void show(Scene scene) {
        Main.currentStage.setScene(scene);
    }

    
    @FXML
    public void initialize(){
        setup(scene);
    }

    @FXML
    GridPane GridPaneWithRecords;
    private void setup(Scene scene) {
        var records = getRecords();
        addRecordsToGridPane(GridPaneWithRecords,records);
    }

    private static void addRecordsToGridPane(GridPane gp, List<String[]> records) {
        SetConstraints(gp,records);
        for (int i = 0; i < records.size(); i++) {
            addRecordToGridPane(gp,i,records.get(i));
        }
    }

    private static void SetConstraints(GridPane gp, List<String[]> records) {
        setColumnConstraints(gp,records.get(0).length);
        setRowConstraints(gp,records.size());
    }

    private static void setColumnConstraints(GridPane gp, int length) {
        while (gp.getColumnConstraints().size() < length) {
            gp.getColumnConstraints().add(new ColumnConstraints());
        }
        gp.getColumnConstraints().forEach(col -> {
            col.setHgrow(Priority.ALWAYS);
            col.setHalignment(HPos.CENTER);
            col.setPercentWidth(100);
        });
    }
    private static void setRowConstraints(GridPane gp, int size) {
        while (gp.getRowConstraints().size() < size) {
            gp.getRowConstraints().add(new RowConstraints());
        }
        gp.getRowConstraints().forEach(row -> {
            row.setPrefHeight(-1);
            row.setVgrow(Priority.ALWAYS);
            row.setValignment(VPos.CENTER);
            row.setMinHeight(-1);
//            How did i found out exactly these fields must be set? Do sout of every row.get... method and compare them
        });
        gp.getRowConstraints().get(0).setVgrow(Priority.ALWAYS);
    }
    private static void addRecordToGridPane(GridPane gp, int i, String[] strings) {
        Label recordLabel;
        for (int j = 0; j < strings.length; j++) {
            recordLabel = new Label(strings[j]);
            recordLabel.setWrapText(true);
            recordLabel.setPadding(new Insets(10));
            gp.add(recordLabel,j,i+(strings[0].contains("User") ? 0 : 1));
        }
    }
    private static List<String[]> getRecords() {
        var list = CSVFileWithRecords.getRecords();
        return list;
    }

    public void goToMainMenu(ActionEvent actionEvent) {
        Main.currentStage.setScene(Main.menuScene);
    }
}
