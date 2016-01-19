package sepr.smew.ces.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public interface DrawableComponent extends Component {
    public void draw(Batch batch, float x, float y);
    public void draw(Batch batch, Vector2 position);
}
