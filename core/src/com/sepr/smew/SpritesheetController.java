package com.sepr.smew;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpritesheetController {
    private final int               frameRows;
    private final int               frameCols;
    private final TextureRegion[][] spriteSheet;

    private TextureRegion currentRegion;

    public SpritesheetController(Texture sheet, int rows, int cols) {
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

    public TextureRegion getCurrentSprite(float delta) {
        // The default implementation obviously has no behaviors.
        return currentRegion;
    }
}
