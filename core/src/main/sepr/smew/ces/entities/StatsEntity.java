package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import sepr.smew.ces.components.*;
import sepr.smew.util.Anim;

/**
 * The one and only Smew! Controlled with the keyboard.
 */
public class StatsEntity extends Entity {
        
    public StatsEntity() {
        this.add(new TextComponent("HEEEY"));
        this.add(new OverlayComponent(10, 10));
    }
    
}
