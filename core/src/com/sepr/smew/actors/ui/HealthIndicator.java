package com.sepr.smew.actors.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;

public class HealthIndicator extends Actor {
    private static Texture full = null;
    private static Texture half = null;

    private int health;

    public HealthIndicator(int startingHealth) {
        health = startingHealth;
    }

    public void changeHealth(int deltaHealth) {
        health += deltaHealth;
    }
}
