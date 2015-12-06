package com.sepr.smew.stage;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;


import com.sepr.smew.utils.WorldManager;


/**
 * This stage simply updates the world physics every act call.
 */

public class PhysicsStage extends Stage {
    public PhysicsStage(Viewport viewport, Batch batch){
        super(viewport, batch);
    }
    
    @Override
    public void act(float deltaTime){
        // Step the world
        WorldManager.getWorld().step(deltaTime, 6, 2);
        super.act(deltaTime);
    }
}
