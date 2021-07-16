package language.learn.quiz.hallOfFame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import language.learn.quiz.Main;
import language.learn.quiz.CSV.CSVFileWithRecords.CSVFileWithRecords;
import language.learn.quiz.lobby.Lobby;

import java.io.IOException;
import java.util.List;


public class HallOfFame {
    static Parent root;
    public static void show() {
        try {
            root = getRoot();
            show(root);
            // Scene shows up, then FXMLLoader calls method initialize(), which continues program
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Parent getRoot() throws IOException {
        if (root == null){
            root = FXMLLoader.load(HallOfFame.class.getResource("HallOfFame.fxml"));
        }
        return root;
    }
    private static void show(Parent root) {
        Main.setRoot(root);
    }

    public static void resetScene() {
        root = null;
    }

}
