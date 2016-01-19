package sepr.smew.ces.systems;

import sepr.smew.ces.components.*;
import sepr.smew.ces.entities.*;
import sepr.smew.util.*;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Manifold;

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.lang.Math;

import java.util.ArrayList;
import com.badlogic.gdx.utils.Array;


public class StatsSystem extends EntitySystem{
    //private static ComponentMapper<TextComponent> tm = ComponentMapper.getFor(TextComponent.class);
    //private static ComponentMapper<PickupComponent> pm = ComponentMapper.getFor(PickupComponent.class);
    
    private final StatsEntity statsEntity;
    private final SmewEntity smewEntity;
    private final Engine engine;
    private final Array<PickupEntity> toRemove;


    public StatsSystem(int priority, World world, Engine engin, SmewEntity smew, StatsEntity stats) {
        super(priority);
        this.statsEntity=stats;
        this.smewEntity=smew;
        this.engine=engin;
        this.toRemove = new Array<PickupEntity>();
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Object a = contact.getFixtureA().getBody().getUserData();
                Object b = contact.getFixtureB().getBody().getUserData();
                if( a == smewEntity && b instanceof PickupEntity ){
                    statsEntity.incPoints();
                    toRemove.add((PickupEntity)b);
                    
                }
                else if( b == smewEntity && a instanceof PickupEntity ){
                    statsEntity.incPoints();
                    toRemove.add((PickupEntity)a);
               }
            }

            @Override
            public void endContact(Contact contact) {
                // stub
            }

            @Override
            public void postSolve(Contact arg0, ContactImpulse arg1) {
                // stub
            }

            @Override
            public void preSolve(Contact arg0, Manifold arg1) {
                // stub
            }
        });
    }

    @Override
    public void update(float deltaTime) {
        statsEntity.setHealthText("×5");
        statsEntity.setPointsText(Integer.toString(statsEntity.points));
        while (toRemove.size>0){
            PickupEntity p = toRemove.pop();
                engine.removeEntity(p);
                p.dispose();
        }
        
    }
}
