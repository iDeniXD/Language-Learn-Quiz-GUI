package language.learn.quiz.transitions;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.util.Duration;
import language.learn.quiz.nodes.AlertLabel;

public class Transitions {
    public static void rootTransitionAppear(Parent root) {
        TranslateTransition t = new TranslateTransition(Duration.millis(80),root);
        t.setFromX(-50);
        t.setToX(0);
        t.setCycleCount(1);
        t.setAutoReverse(true);
        t.play();
    }

    // Transitions
    public static void doAppearTransitions(AlertLabel alertLabel) {
        doScaleAppearTransition(alertLabel);
    }
    public static void doDisappearTransitions(ObservableList<Node> parent, AlertLabel alertLabel) {
        doScaleDisappearTransition(parent,alertLabel);
    }

    // Appear transitions
    private static void doScaleAppearTransition(AlertLabel alertLabel) {
        ScaleTransition t = getScaleTransitionAppear(alertLabel);
        // When finishes, another transition starts
        t.setOnFinished(e -> doTranslateTransition(alertLabel));
        t.play();
    }
    private static void doTranslateTransition(AlertLabel alertLabel) {
        TranslateTransition t = getTranslateTransition(alertLabel);
        t.play();
    }
    //Disappear transitions
    private static void doScaleDisappearTransition(ObservableList<Node> parent, AlertLabel alertLabel) {
        ScaleTransition scaleTransition = getScaleTransitionDisappear(alertLabel);
        scaleTransition.play();
        scaleTransition.setOnFinished(e -> parent.remove(alertLabel));
    }



    // Methods for Scale Transition
    private static ScaleTransition getScaleTransitionAppear(AlertLabel alertLabel) {
        return getScaleTransition(alertLabel);
    }
    private static ScaleTransition getScaleTransitionDisappear(AlertLabel alertLabel) {
        ScaleTransition st = getScaleTransition(alertLabel);
        st.setFromX(st.getToX());
        st.setFromY(st.getToY());
        st.setToX(0f);
        st.setToY(0f);
        return st;
    }
    private static ScaleTransition getScaleTransition(AlertLabel alertLabel) {
        ScaleTransition scaleTransition =
                new ScaleTransition(Duration.millis(200), alertLabel);
        scaleTransition.setFromX(0f);
        scaleTransition.setFromY(0f);
        scaleTransition.setToX(1f);
        scaleTransition.setToY(1f);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        return scaleTransition;
    }
    // Method for Translate Transition
    private static TranslateTransition getTranslateTransition(AlertLabel alertLabel) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), alertLabel);
        translateTransition.setByY(alertLabel.getLayoutY()-alertLabel.getHeight()-alertLabel.getLayoutY()-10);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        return translateTransition;
    }
}
