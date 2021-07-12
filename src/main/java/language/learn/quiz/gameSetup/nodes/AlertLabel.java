package language.learn.quiz.gameSetup.nodes;

import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class AlertLabel extends Label {
    public AlertLabel() {
        appear();
    }

    public AlertLabel(String text) {
        super(text);
        appear();
    }

    public AlertLabel(String text, Node graphic) {
        super(text, graphic);
        appear();
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    private void appear() {
        ScaleTransition scaleTransition = getScaleTransitionAppear();
        scaleTransition.setCycleCount(1);
        scaleTransition.play();
    }

    private ScaleTransition getScaleTransitionAppear() {
        return getScaleTransition();
    }

    public void disappear(ObservableList<Node> children) {
        ScaleTransition scaleTransition = getScaleTransitionDisappear();
        scaleTransition.setCycleCount(1);
        scaleTransition.play();
        scaleTransition.setOnFinished(e -> children.remove(this));
    }

    private ScaleTransition getScaleTransitionDisappear() {
        ScaleTransition st = getScaleTransition();
        st.setFromX(st.getToX());
        st.setFromY(st.getToY());
        st.setToX(0f);
        st.setToY(0f);
        return st;
    }

    private ScaleTransition getScaleTransition() {
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(200), this);
        scaleTransition.setFromX(0f);
        scaleTransition.setFromY(0f);
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        return scaleTransition;
    }


}
