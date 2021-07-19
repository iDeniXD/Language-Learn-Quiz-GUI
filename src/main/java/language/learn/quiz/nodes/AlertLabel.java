package language.learn.quiz.nodes;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import language.learn.quiz.transitions.Transitions;

import java.util.Timer;
import java.util.TimerTask;

public class AlertLabel extends Label {

    public AlertLabel(String text) {
        super(text);
        // for css file
        getStyleClass().add("shadow");
        getStyleClass().add("popup");
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        appear();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    private void appear() {
        // Scale Transition -> Translate transition
        Transitions.doAppearTransitions(this);
    }

    public void disappear(ObservableList<Node> parent) {
        // Scale Transition
        Transitions.doDisappearTransitions(parent,this);
    }


    public void setTimerToSelfDestruct(AnchorPane rootAnchorPane, long length) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    disappear(rootAnchorPane.getChildren());
                });

            }
        };
        timer.schedule(task, length);
    }

    public void setPositionRelatively(Node node) {
        Bounds boundsInScene = node.localToScene(node.getBoundsInLocal());
        setLayoutX(boundsInScene.getMinX());
        setLayoutY(boundsInScene.getMinY());
    }
}
