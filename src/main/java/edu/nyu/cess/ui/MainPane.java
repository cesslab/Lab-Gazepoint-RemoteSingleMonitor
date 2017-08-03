package edu.nyu.cess.ui;

import edu.nyu.cess.scene.SceneName;
import edu.nyu.cess.scene.SceneNavigator;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainPane extends StackPane {
    private static SlidingNotification slidingNotification;

    public MainPane(SceneName firstScene) {
        SceneNavigator.initialize(this, firstScene);
        slidingNotification = new SlidingNotification(this);
    }

    public static void displaySlidingMessage(String message) {
        slidingNotification.show(message);
    }

    /**
     * Changes the scene to the Node specified.
     * @param node Node object to set
     */
    public void setPane(Node node) {
        this.getChildren().setAll(node);
    }
}
