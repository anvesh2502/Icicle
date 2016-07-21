package com.game.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class IciclesScreen implements Screen
{
    ExtendViewport iciclesViewport;

    Icicle icicle;

    Player player;

    ShapeRenderer renderer;

    Icicles icicles;

    ScreenViewport hudViewport;

    SpriteBatch batch;

    BitmapFont font;

    Constants.Difficulty difficulty;

    int topScore=0;


    IciclesScreen(IciclesGame iciclesGame, Constants.Difficulty difficulty)
    {
        this.difficulty=difficulty;
    }



    @Override
    public void show()
    {
        iciclesViewport=new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);

        renderer=new ShapeRenderer();

        renderer.setAutoShapeType(true);

        player=new Player(iciclesViewport);

        icicle=new Icicle(new Vector2(Constants.WORLD_SIZE/2,Constants.WORLD_SIZE/2));

        icicles=new Icicles(iciclesViewport,difficulty);

        hudViewport=new ScreenViewport();

        batch=new SpriteBatch();

        font=new BitmapFont();

        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);



    }

    @Override
    public void render(float delta)
    {
        iciclesViewport.apply(true);
        player.update(delta);
        icicles.update(delta);

        if(player.hitByIcicles(icicles))
        {
            icicles.init();
        }


        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(iciclesViewport.getCamera().combined);

        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        player.render(renderer);
        icicles.render(renderer);
        renderer.end();

        hudViewport.apply();

        // TODO: Set the SpriteBatch's projection matrix
        batch.setProjectionMatrix(hudViewport.getCamera().combined);

        // TODO: Begin the SpriteBatch
        batch.begin();

        // TODO: Draw the number of player deaths in the top left
        font.draw(batch, "Deaths: " + player.deaths,
                Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN);

        // TODO: Draw the score and top score in the top right
        font.draw(batch, "Score: " + icicles.iciclesDodged + "\nTop Score: " + topScore,
                hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);

        // TODO: Show Difficulty level in the top left
        font.draw(batch, "Deaths: " + player.deaths + "\nDifficulty: " + difficulty.label,
                Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN);
        font.draw(batch, "Score: " + icicles.iciclesDodged + "\nTop Score: " + topScore,
                hudViewport.getWorldWidth() - Constants.HUD_MARGIN, hudViewport.getWorldHeight() - Constants.HUD_MARGIN,
                0, Align.right, false);



        // TODO: End the SpriteBatch
        batch.end();





    }

    @Override
    public void resize(int width, int height)
    {
        iciclesViewport.update(width,height,true);
        player.init();
        icicles.init();
        hudViewport.update(width, height, true);

        // TODO: Set font scale to min(width, height) / reference screen size
        font.getData().setScale(Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE);
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
    public void dispose()
    {
     renderer.dispose();
     batch.dispose();
     font.dispose();
    }
}
