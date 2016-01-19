package sepr.smew.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Anim {
    public static Animation loadAnimation(String filepath, int rows, int cols, float dur) {
        Texture tex = new Texture(filepath);
        TextureRegion[][] regions = TextureRegion.split(tex, tex.getWidth() / cols, tex.getHeight() / rows);
        TextureRegion[] animRegions = new TextureRegion[rows * cols];
        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                animRegions[index++] = regions[row][col];
            }
        }
        Animation anim = new Animation(dur, animRegions);
        anim.setPlayMode(Animation.PlayMode.LOOP);
        return anim;
    }
}
