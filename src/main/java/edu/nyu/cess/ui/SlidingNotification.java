package edu.nyu.cess.ui;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

// TODO: Hook into application
public class SlidingNotification extends Region {
    private static final Label textLabel = new Label("");
    private static SlidingNotification instance;

    private SlidingNotification(Stage primaryStage) {
        getStyleClass().add("sliding-notification");
        getChildren().add(textLabel);

        translateYProperty().bind(primaryStage.heightProperty().multiply(0.1));
        textLabel.translateYProperty().bind(heightProperty().multiply(0.5).
                subtract(textLabel.getFont().getSize() + 3));
        textLabel.prefWidthProperty().bind(widthProperty());
        textLabel.setAlignment(Pos.CENTER);
        textLabel.setGraphicTextGap(5);
        setMaxWidth(0);

        setMaxHeight(55);
    }

    public static void initialize(Stage mainPane) {
        if (instance == null) {
            instance = new SlidingNotification(mainPane);
        }
    }

    private void showNotification(String message) {
        textLabel.setText(message);
        double currentWidth = message.length() * textLabel.getFont().getSize() + 20;
        instance.setMaxWidth(currentWidth);

        Timeline transition = new Timeline(new KeyFrame(Duration.ZERO,
                new KeyValue(instance.translateXProperty(), currentWidth, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(400),
                        new KeyValue(instance.translateXProperty(), 0, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(1600),
                        new KeyValue(instance.translateXProperty(), 0, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.millis(2000),
                        new KeyValue(instance.translateXProperty(), currentWidth, Interpolator.EASE_BOTH)));
        transition.play();
    }

    public static Region getInstance() {
        return instance;
    }

    public static void show(String message) {
        if (instance != null) {
            instance.showNotification(message);
        }
    }
}
