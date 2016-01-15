package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class SpritesheetComponent implements Component {
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

    //YES I KNOW. Behaviour does not belong here, these are convenience methods.
    public TextureRegion currentFrame() {
        return animation.getKeyFrame(stateTime);
    }

    public void update(float deltaTime){
        stateTime += deltaTime;
    }
}
