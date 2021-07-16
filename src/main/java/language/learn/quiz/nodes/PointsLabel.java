package language.learn.quiz.nodes;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.security.Key;

public class PointsLabel extends Label {
    public PointsLabel() {
        initialize();
    }

    public PointsLabel(String text) {
        super(text);
        initialize();
    }

    public PointsLabel(String text, Node graphic) {
        super(text, graphic);
        initialize();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
    
    private void initialize(){
        setListener();
    }

    private void setListener() {
        this.textProperty().addListener(e -> {
            doHighlightTransition();
        });
    }

    private void doHighlightTransition() {
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(80), this);
        scaleTransition.setFromX(3f);
        scaleTransition.setFromY(3f);
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();
    }

}
