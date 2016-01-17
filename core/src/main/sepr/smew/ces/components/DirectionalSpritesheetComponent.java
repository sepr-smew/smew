package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

/**
 * Controls the various animations that the Entity can perform.
 */
public class DirectionalSpritesheetComponent implements BaseSpritesheetComponent {
    public Animation[] animations;
    public float stateTime;
    public float angle;

    public DirectionalSpritesheetComponent(Animation[] animations) {
        stateTime = 0f;
        this.animations = animations;
        angle = 0f;
    }
    
    public void advance(float deltaTime){
        stateTime+=deltaTime;  // leaves open for future animated things.
    }

    public TextureRegion currentFrame() {
        int ind = (2 + (int)Math.rint(angle/90f)) % 4;
        return animations[ind].getKeyFrame(stateTime);
    }
}
