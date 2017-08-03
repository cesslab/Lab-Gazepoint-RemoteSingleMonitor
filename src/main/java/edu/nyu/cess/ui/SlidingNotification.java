package edu.nyu.cess.ui;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class SlidingNotification extends Region {
    private Label textLabel = new Label("");

    public SlidingNotification(MainPane mainPane) {
        getStyleClass().add("head-hint");
        getChildren().add(textLabel);

        translateYProperty().bind(mainPane.heightProperty().multiply(0.1));
        textLabel.translateYProperty().bind(heightProperty().multiply(0.5).
                subtract(textLabel.getFont().getSize() + 3));
        textLabel.prefWidthProperty().bind(widthProperty());
        textLabel.setAlignment(Pos.CENTER);
        textLabel.setGraphicTextGap(5);
        setMaxWidth(0);

        setMaxHeight(55);

    }

    public void show(String message) {
        textLabel.setText(message);
        double currentWidth = message.length() * textLabel.getFont().getSize() + 20;
        setMaxWidth(currentWidth);

        Timeline transition = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(translateXProperty(), currentWidth, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(400),
                        new KeyValue(translateXProperty(), 0, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(1600),
                        new KeyValue(translateXProperty(), 0, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(2000),
                        new KeyValue(translateXProperty(), currentWidth, Interpolator.EASE_BOTH)));
        transition.play();
    }
}
