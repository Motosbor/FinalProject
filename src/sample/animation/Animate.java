package sample.animation;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animate {
    private RotateTransition rotateTransition;
    private TranslateTransition transition;

    public Animate(Node node){
        transition = new TranslateTransition(Duration.millis(10000),node);
        transition.setFromX(0f);
        transition.setByX(600f);
        transition.setCycleCount(Integer.MAX_VALUE);
        transition.setAutoReverse(true);
        rotateTransition = new RotateTransition(Duration.millis(5000),node);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Integer.MAX_VALUE);
        rotateTransition.setAutoReverse(true);
    }

    public void playAnimation(){
        transition.playFromStart();
        rotateTransition.play();
    }
}
