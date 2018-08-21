package clouds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import helpers.GameInfo;

public class Cloud extends Sprite {

    private World world;
    private Body body;

    public Cloud (World world, String name) {
        super(new Texture(name));
        this.world = world;
        setPosition(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 300);
        createBody();
    }

    void createBody(){

        BodyDef bodyDef = new BodyDef();

        //a static body is not affected by gravity or any forces
        //a kinematic body is not affected by gravity but it is affected by other forces
        //a dynamic body is affected by gravity and other forces
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(getX() / GameInfo.PPM, getY() / GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / GameInfo.PPM, (getHeight() / 2) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1; //set body mass

        Fixture fixture = body.createFixture(fixtureDef);

        //set body's name as "Cloud"
        fixture.setUserData("Cloud");

        shape.dispose();
    }
}
