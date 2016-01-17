package sepr.smew.display;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sepr.smew.ecs.components.PointsComponent;
import sepr.smew.ecs.entities.PointsEntity;

/**
 * Health and Score UI.
 */
public class PointsActor extends Actor {
    private PointsEntity entity;
    private ComponentMapper<PointsComponent> pm = ComponentMapper.getFor(PointsComponent.class);

    private BitmapFont font = new BitmapFont();

    public PointsActor(PointsEntity entity) {
        this.entity = entity;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        PointsComponent pc = pm.get(entity);
        font.draw(batch, "Health: " + String.valueOf(pc.health), 50, 310);
        font.draw(batch, String.valueOf(pc.score) + " points", 410, 310);
    }
}
