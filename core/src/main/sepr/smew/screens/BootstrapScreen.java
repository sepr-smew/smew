package sepr.smew.screens;

import com.badlogic.gdx.Gdx;
import sepr.smew.SmewFighters;

/**
 * The first screen. Think of it as the init screen launched by the game
 * "bootloader". This screen simply exists to take complexity out of the game
 * class.
 */
public class BootstrapScreen extends AbstractScreen {
    public BootstrapScreen(final SmewFighters game) {
        super(game);
    }
}
