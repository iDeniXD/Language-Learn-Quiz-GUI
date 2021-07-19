package language.learn.quiz.nodes;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class PointsLabel extends Label {

    public PointsLabel(String text) {
        super(text);
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
        // When text is changed
        this.textProperty().addListener(e -> {
            doHighlightTransition();
        });
    }

    private void doHighlightTransition() {
        // A little enlargement in order to attract attention
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
