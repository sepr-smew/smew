package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpritesheetComponent implements Component {
    /**
     * An array of texture regions that represent the frames of the sheet.
     */
    public TextureRegion[] frames;

    /**
     * The current frame, stored as an index of frames.
     */
    public int currentFrame = 0;

    /**
     * The rate of animation. Set to 0 if paused.
     */
    public float animRate = 0.0f;
}
