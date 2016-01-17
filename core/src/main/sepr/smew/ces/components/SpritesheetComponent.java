package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

/**
 * Controls the various animations that the Entity can perform.
 */
public class SpritesheetComponent implements BaseSpritesheetComponent {
    public Animation animation;
    public float stateTime;

    public SpritesheetComponent(float frameDuration, Array<? extends TextureRegion> keyFrames, Animation.PlayMode playMode) {
        stateTime = 0f;
        animation = new Animation(frameDuration, keyFrames, playMode);
    }

    public SpritesheetComponent(float frameDuration, Array<? extends TextureRegion> keyFrames) {
        stateTime = 0f;
        animation = new Animation(frameDuration, keyFrames, Animation.PlayMode.LOOP);
    }
    
    public void advance(float deltaTime){
        stateTime+=deltaTime;  // leaves open for future animated things.
    }
    
    public TextureRegion currentFrame() {
        return animation.getKeyFrame(stateTime);
    }
}
