package com.sepr.smew.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;

import com.sepr.smew.SmewFighters;

/**
 * The main menu with a title and a play button and other things.
 */
public class StartupScreen extends AbstractScreen {
    public StartupScreen(final SmewFighters game) {
        super(game, new Stage(game.viewport, game.batch));
    }
}
