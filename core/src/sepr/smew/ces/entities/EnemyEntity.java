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
 
 public class EnemyEntity extends Entity {
    public EnemyEntity(World world, float x, float y) {
        this.add(PhysicsComponent.dynamicBox(this, world, x, y, 12, 12));
        this.add(new EnemyComponent());
        this.add(new TextureComponent("Sprites/Goose/Goose_sprite.png", 12, 12));
        this.add(new RenderPriorityComponent(1));
        //this.add(new DirectionalSpritesheetComponent(anims)); // when we have goose spritesheets.
    }
}

