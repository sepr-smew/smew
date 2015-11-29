package com.sepr.smew.screens;

import com.sepr.smew.SmewFighters;
import com.sepr.smew.actors.LogoActor;

/**
 * The main menu with a title and a play button and other things.
 */
public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen(final SmewFighters gam) {
        super(gam);
        getStage().addActor(new LogoActor());
    }
}
