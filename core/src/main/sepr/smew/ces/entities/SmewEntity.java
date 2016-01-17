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
public class SmewEntity extends Entity {
    public SmewEntity(World world) {
        this.add(new TextureComponent());
        this.add(new BoundsComponent(20, 40));
        
        Animation[] anims = new Animation[]{
        Anim.loadAnimation("Sprites/Smew/Smew_back_spritesheet.png", 1, 9, 0.1f),
        Anim.loadAnimation("Sprites/Smew/Smew_right_spritesheet.png", 1, 12, 0.1f),
        Anim.loadAnimation("Sprites/Smew/Smew_front_spritesheet.png", 1, 8, 0.1f),
        Anim.loadAnimation("Sprites/Smew/Smew_left_spritesheet.png", 1, 12, 0.1f)
        };
        
        this.add(new DirectionalSpritesheetComponent(anims));
        this.add(PhysicsComponent.dynamicBox(world, 40, 40, 20, 40));
        this.add(new SmewMovementComponent(100f));
    }
}
