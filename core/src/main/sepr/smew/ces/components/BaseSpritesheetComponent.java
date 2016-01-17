package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public interface BaseSpritesheetComponent extends Component {
    public void advance(float deltaTime);

    public TextureRegion currentFrame();
}
