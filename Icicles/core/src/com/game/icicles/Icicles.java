package com.game.icicles;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.glass.ui.View;

public class Icicles
{
  DelayedRemovalArray<Icicle> icicleList;
  Viewport viewport;

    Icicles(Viewport viewport)
    {
        this.viewport=viewport;
        init();
    }

    void init()
    {
        icicleList=new DelayedRemovalArray<Icicle>(false,100);
    }

    public void update(float delta)
    {
        // TODO: Replace hard-coded spawn rate with a constant
        if (MathUtils.random() < delta * Constants.ICICLE_SPAWNS_PER_SECOND)
        {
            // TODO: Add a new icicle at the top of the viewport at a random x position
            Vector2 newIciclePosition = new Vector2(
                    MathUtils.random() * viewport.getWorldWidth(),
                    viewport.getWorldHeight()
            );
            Icicle newIcicle = new Icicle(newIciclePosition);
            icicleList.add(newIcicle);
        }



        // TODO: Update each icicle
        for (Icicle icicle : icicleList) {
            icicle.update(delta);
        }


        icicleList.begin();

        // TODO: Remove any icicle completely off the bottom of the screen
        for (int i = 0; i < icicleList.size; i++) {
            if (icicleList.get(i).position.y < -Constants.ICICLES_HEIGHT) {
                icicleList.removeIndex(i);
            }
        }
        // TODO: End removal session
        icicleList.end();
    }

    public void render(ShapeRenderer renderer) {
        // TODO: Set ShapeRenderer Color
        renderer.setColor(Constants.ICICLE_COLOR);

        // TODO: Render each icicle
        for (Icicle icicle : icicleList) {
            icicle.render(renderer);
        }
    }


}
