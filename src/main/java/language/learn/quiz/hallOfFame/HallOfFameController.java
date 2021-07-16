package language.learn.quiz.hallOfFame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import language.learn.quiz.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.Main;
import language.learn.quiz.lobby.Lobby;

import java.util.List;

public class HallOfFameController {


    @FXML
    public void initialize(){
        setup();
    }

    @FXML
    GridPane GridPaneWithRecords;
    private void setup() {
        // Show table of records
        showRecords();
    }

    private void showRecords() {
        // Load records from CSV file
        var records = getRecords();
        // Put them into the gridPane
        addRecordsToGridPane(GridPaneWithRecords,records);
    }
    private List<String[]> getRecords() {
        return CSVFileWithRecords.getRecords();
    }

    private void addRecordsToGridPane(GridPane gp, List<String[]> records) {
        // Set Condtraints of the gridPane - make it look acceptable
        SetConstraints(gp,records);
        // Add each record to the gridPane
        for (int i = 0; i < records.size(); i++) {
            addRecordToGridPane(gp,i,records.get(i));
        }
    }

    private void SetConstraints(GridPane gp, List<String[]> records) {
        // Working with columns
        setColumnConstraints(gp,records.get(0).length);
        // Working with rows
        setRowConstraints(gp,records.size());
    }

    private void setColumnConstraints(GridPane gp, int length) {
        // Add as much columns as there is in each record line
        while (gp.getColumnConstraints().size() < length) {
            gp.getColumnConstraints().add(new ColumnConstraints());
        }
        // Setup each column
        gp.getColumnConstraints().forEach(col -> {
            col.setHgrow(Priority.ALWAYS);
            col.setHalignment(HPos.CENTER);
            col.setPercentWidth(100);
        });
    }
    private void setRowConstraints(GridPane gp, int size) {
        // Add as much rows as there are records
        while (gp.getRowConstraints().size() < size) {
            gp.getRowConstraints().add(new RowConstraints());
        }
        // Setup each row
        gp.getRowConstraints().forEach(row -> {
            row.setPrefHeight(-1);
            row.setMinHeight(-1);
            row.setMaxHeight(200f);
            row.setVgrow(Priority.ALWAYS);
            row.setValignment(VPos.CENTER);
//            How did i found out exactly these fields must be set? Do sout of every row.get... method and compare them
        });
    }
    private void addRecordToGridPane(GridPane gp, int i, String[] strings) {
        Label recordLabel;
        for (int j = 0; j < strings.length; j++) {
            recordLabel = new Label(strings[j]);
            recordLabel.setPadding(new Insets(10));
            if (i==0)
                recordLabel.setStyle("-fx-font-weight: BOLD;");
            gp.add(recordLabel,j,i);
        }
    }

    public void goToLobby(ActionEvent actionEvent) {
        Lobby.loadScene();
    }
}
