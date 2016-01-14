package sepr.smew.ces.entities;



import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import sepr.smew.ces.components.*;

public class SmewEntity extends Entity {
    //public SmewEntity(World world) {
    public SmewEntity(World world) {
        //this.add(PhysicsComponent.dynamicBox(world, 50, 50, 100, 100));
        this.add(new TextureComponent());
        
        Texture smew_sprites = new Texture("Sprites/Smew/Smew_front_spritesheet.png");
        int frame_count = 8;
        Array frames = new Array(TextureRegion.split(smew_sprites, smew_sprites.getWidth()/frame_count, smew_sprites.getHeight())[0]);
        
        this.add(new SpritesheetComponent(frames));
        this.add(PhysicsComponent.dynamicBox(world, 100, 100, 20, 40));
        this.add(new KeyboardMovementComponent(100f));
    }
}
