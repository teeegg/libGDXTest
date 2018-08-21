package scenes;

import clouds.Cloud;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.MyGdxGame;
import health.HealthBar;
import player.Player;
import sun.applet.Main;
import helpers.GameInfo;

public class MainMenu implements Screen, ContactListener {

    private MyGdxGame game;

    private Texture bg, healthBG, healthFG, healthBR;

    private Player player;

    private HealthBar healthBar;

    private World world;

    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    public MainMenu(MyGdxGame game){
        this.game = game;

        box2DCamera = new OrthographicCamera();
        box2DCamera.setToOrtho(false, GameInfo.WIDTH / GameInfo.PPM,
                GameInfo.HEIGHT / GameInfo.PPM);

        box2DCamera.position.set(GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f, 0);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, -9.8f), true);
        //set gravity on the ground at 9.8, set doSleep true to increase efficiency, save performance
        //doSleep: if the body is not moving or nothing is happening to the body, simply ignore the body. No calculation

        world.setContactListener(this);

        bg = new Texture("game cloud BG.jpeg");

        healthBG = new Texture("Health Background.png");
        healthFG = new Texture("Health Foreground.png");
        //healthBR = new Texture("Health Border.png");

        player = new Player(world,"soldier.png", GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2);

        Cloud c = new Cloud(world, "cloud.png");

        healthBar = new HealthBar();
    }

    /**
     * Move the player on key clicked.
     * @param dt
     */
    void update(float dt) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

//            player.getBody().applyLinearImpulse(new Vector2(-0.1f, 0),
//                    player.getBody().getWorldCenter(), true);

            player.getBody().applyForce(new Vector2(-8f, 0),
                    player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {

//            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
//                    player.getBody().getWorldCenter(), true);

            player.getBody().applyForce(new Vector2(8f, 0),
                    player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

//            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
//                    player.getBody().getWorldCenter(), true);

            player.getBody().applyForce(new Vector2(0, 50f),
                    player.getBody().getWorldCenter(), true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

//            player.getBody().applyLinearImpulse(new Vector2(0.1f, 0),
//                    player.getBody().getWorldCenter(), true);

            player.getBody().applyForce(new Vector2(0, -50f),
                    player.getBody().getWorldCenter(), true);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(0,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg,0,0);

        //position the player on the screen
        game.getBatch().draw(player, player.getX() - player.getWidth() / 2f,
                player.getY() - player.getHeight() / 2f);

        //game.getBatch().draw(healthBar, HealthBar.healthBarPositionX, HealthBar.healthBarPositionY);


        //position the cloud on the screen
        //game.getBatch().draw(cloud, GameInfo.WIDTH / 2f, GameInfo.HEIGHT / 2f - 300);

        //game.getBatch().draw(healthBG, GameInfo.WIDTH-300, GameInfo.HEIGHT-30);
        //game.getBatch().draw(healthFG, GameInfo.WIDTH-300, GameInfo.HEIGHT-30);
        //game.getBatch().draw(healthBR, GameInfo.WIDTH-300, GameInfo.HEIGHT-30);

        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        bg.dispose();
        player.getTexture().dispose();
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture firstBody, secondBody;

        if (contact.getFixtureA().getUserData() == "Player") {
            firstBody = contact.getFixtureA();
            secondBody = contact.getFixtureB();
        } else {
            firstBody = contact.getFixtureB();
            secondBody = contact.getFixtureB();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
