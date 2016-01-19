package sepr.smew.ces.systems;

import sepr.smew.ces.components.*;
import sepr.smew.ces.entities.*;
import sepr.smew.util.*;
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

import com.badlogic.gdx.graphics.OrthographicCamera;

import java.lang.Math;

import java.util.ArrayList;


public class StatsSystem extends EntitySystem{
    private static ComponentMapper<TextComponent> tm = ComponentMapper.getFor(TextComponent.class);
    
    private StatsEntity statsEntity;


    public StatsSystem(int priority, StatsEntity statsEntity) {
        super(priority);
        this.statsEntity=statsEntity;
    }

    @Override
    public void update(float deltaTime) {
        statsEntity.setHealthText("×5");
        statsEntity.setPointsText("053");
    }
}
