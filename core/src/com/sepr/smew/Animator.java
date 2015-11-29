package com.sepr.smew;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    /**
     * The number of rows in the spritesheet.
     */
    private final int frameRows;

    /**
     * The number of columns in the spritesheet.
     */
    private final int frameCols;

    /**
     * A matrix of TextureRegions.
     */
    private final TextureRegion[][] spriteSheet;

    /**
     * A reference to the current TextureRegion being used.
     */
    private TextureRegion currentRegion;

    public Animator(Texture sheet, int rows, int cols) {
        // TODO(avinashbot): Throw an error if rows/cols is a stupid value.
        frameRows = rows;
        frameCols = cols;

        spriteSheet = TextureRegion.split(
            sheet, sheet.getWidth() / cols, sheet.getHeight() / rows
        );
        currentRegion = spriteSheet[0][0];
    }

    /**
     * Set the sprite to the one at the given row and column on the
     * spritesheet.
     */
    protected void setSprite(int row, int col) {
        // TODO(avinashbot): Throw an error if out of range.
        currentRegion = spriteSheet[row][col];
    }

    /**
     * An overrideable method that uses setSprite to implement animation.
     */
    public void update(float delta) {
        // Here is where a subclass would update currentRegion.
    }

    /**
     * Get the current sprite to be rendered.
     */
    public TextureRegion getCurrentSprite() {
        return currentRegion;
    }
}
