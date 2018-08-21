package health;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import helpers.GameInfo;

public class HealthBar {
    private Sprite healthBarBG;
    private Sprite healthBarFG;

    public static final int healthBarPositionX = GameInfo.WIDTH-300;
    public static final int healthBarPositionY = GameInfo.HEIGHT -30;

    public HealthBar () {
        //super(new Texture("Health Foreground.png"));
        healthBarBG.setTexture(new Texture("Health Background.png"));
        healthBarFG.setTexture(new Texture("Health Foreground.png"));

        healthBarBG.setX(healthBarPositionX);
        healthBarBG.setY(healthBarPositionY);
        healthBarFG.setX(healthBarPositionX);
        healthBarFG.setY(healthBarPositionY);

        //setPosition(healthBarPositionX, healthBarPositionY);
    }

}
