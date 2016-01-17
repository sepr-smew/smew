package sepr.smew.display;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import sepr.smew.ecs.components.PhysicsComponent;
import sepr.smew.ecs.entities.SmewEntity;

/**
 * The display component of the smew.
 */
public class SmewActor extends Actor {
    private SmewEntity entity;
    private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
    private float stateTime = 0f;
    private Vector2 pos;

    private final Animation upAnim = loadAnimation("Sprites/Smew/Smew_back_spritesheet.png", 1, 9, 0.1f);
    private final Animation dwAnim = loadAnimation("Sprites/Smew/Smew_front_spritesheet.png", 1, 8, 0.1f);
    private final Animation ltAnim = loadAnimation("Sprites/Smew/Smew_left_spritesheet.png", 1, 12, 0.1f);
    private final Animation rtAnim = loadAnimation("Sprites/Smew/Smew_right_spritesheet.png", 1, 12, 0.1f);
    private Animation curAnim = dwAnim;

    public SmewActor(SmewEntity entity) {
        this.entity = entity;
    }

    @Override
    public void act(float deltaTime) {
        stateTime += deltaTime;

        PhysicsComponent pc = pm.get(entity);
        pos = pc.drawPos();
        Vector2 vel = pc.body.getLinearVelocity();
        if (vel.x >= 5) {
            curAnim = rtAnim;
        } else if (vel.x <= -5) {
            curAnim = ltAnim;
        } else if (vel.y > 0) {
            curAnim = upAnim;
        } else if (vel.y < 0) {
            curAnim = dwAnim;
        } else {
            stateTime -= deltaTime;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(curAnim.getKeyFrame(stateTime, true), pos.x, pos.y, 25, 35);
    }

    private static Animation loadAnimation(String filepath, int rows, int cols, float dur) {
        Texture tex = new Texture(filepath);
        TextureRegion[][] regions = TextureRegion.split(tex, tex.getWidth() / cols, tex.getHeight() / rows);
        TextureRegion[] animRegions = new TextureRegion[rows * cols];
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                animRegions[index++] = regions[row][col];
            }
        }
        return new Animation(dur, animRegions);
    }

}
