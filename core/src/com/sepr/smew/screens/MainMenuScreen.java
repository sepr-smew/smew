package com.sepr.smew.screens;

import com.sepr.smew.SmewFighters;
import com.sepr.smew.actors.LogoActor;

public class MainMenuScreen extends AbstractScreen {
    public MainMenuScreen(final SmewFighters gam) {
        super(gam);
        getStage().addActor(new LogoActor());
    }
}
