package edu.nyu.cess.controller;

import edu.nyu.cess.scene.SceneName;

public interface Updatable {
    /**
     * Performs any updates required when navigating to an Updatable controller.
     * @param previousSceneName The previous scene name
     */
    void onNavigateUpdate(SceneName previousSceneName);
}
