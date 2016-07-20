package com.game.icicles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;


public class IciclesScreen implements Screen
{
    ExtendViewport iciclesViewport;

    Icicle icicle;

    ShapeRenderer renderer;




    @Override
    public void show()
    {
        iciclesViewport=new ExtendViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);

        renderer=new ShapeRenderer();

        renderer.setAutoShapeType(true);

        icicle=new Icicle(new Vector2(Constants.WORLD_SIZE/2,Constants.WORLD_SIZE/2));


    }

    @Override
    public void render(float delta)
    {
    iciclesViewport.apply(true);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(iciclesViewport.getCamera().combined);

        renderer.begin();
        renderer.set(ShapeRenderer.ShapeType.Filled);
        icicle.render(renderer);
        renderer.end();



    }

    @Override
    public void resize(int width, int height)
    {
        iciclesViewport.update(width,height,true);
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
    }
}