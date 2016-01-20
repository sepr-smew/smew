package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;

import sepr.smew.util.Anim;

import sepr.smew.ces.components.*;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;

public class PickupEntity extends Entity {
    public Body body;
    public PickupEntity(World world, float x, float y) {
        this(world, x, y, 6, 6);
    }
    public PickupEntity(World world, float x, float y, float width, float height) {
        PhysicsComponent phy = PhysicsComponent.staticBox(this, world, x, y, width, height);
        this.add(new RenderPriorityComponent(3));
        this.add(new TextureComponent(width, height));
        this.add(new SpritesheetComponent(Anim.loadAnimation("Star.png", 1, 8, 0.1f)));
        body = phy.body;
        this.add(phy);
    }
    public void dispose(){
        body.getWorld().destroyBody(body);
    }
}

