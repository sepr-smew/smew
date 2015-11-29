package com.sepr.smew;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator {
    private final int               frameRows;
    private final int               frameCols;
    private final TextureRegion[][] spriteSheet;

    private TextureRegion currentRegion;

    public Animator(Texture sheet, int rows, int cols) {
        // TODO(avinashbot): Throw an error if rows/cols is a stupid value.
        frameRows = rows;
        frameCols = cols;

        spriteSheet = TextureRegion.split(
            sheet, sheet.getWidth()/cols, sheet.getHeight()/rows
        );
        currentRegion = spriteSheet[0][0];
    }

    protected void setSprite(int row, int col) {
        // TODO(avinashbot): Throw an error if out of range.
        currentRegion = spriteSheet[row][col];
    }

    public void update(float delta) {
        // Here is where a subclass would update currentRegion.
    }

    public TextureRegion getCurrentSprite() {
        return currentRegion;
    }
}
