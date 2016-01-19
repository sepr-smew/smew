package sepr.smew.ces.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import sepr.smew.ces.components.*;
import com.badlogic.gdx.graphics.Color;

/**
 * The one and only Smew! Controlled with the keyboard.
 */
public class StatsEntity extends Entity {
    TextComponent healthText;
    TextComponent pointsText;
    public int points;
    
    public StatsEntity() {
        points = 0;
        CompositeDrawableComponent com = new CompositeDrawableComponent();
        healthText = new TextComponent();
        pointsText = new TextComponent();
        
        com.add(new ShapeComponent(new Color(0f, 0f, 0f, 0.3f), 124, 6), -3f, -4.5f);
        com.add(healthText, 6, 0);
        com.add(new TextureComponent("Heart.png", 5, 5), 0, -4);
        com.add(pointsText, 19, 0);
        com.add(new TextComponent("Collect 10 stars."), 30, 0);
        com.add(new TextComponent("Round 1"), 100, 0);
        
        this.add(com);
        this.add(new OverlayComponent(5, 78));
        this.add(new RenderPriorityComponent(4));
    }
    
    public void setHealthText(String str){
        healthText.text = str;
    }
    
    public void setPointsText(String str){
        pointsText.text = str;
    }
    
    public void incPoints(){
        points++;
    }
    
    
}
