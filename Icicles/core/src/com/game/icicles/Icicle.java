package com.game.icicles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;


public class Icicle
{
    public static final String TAG = Icicle.class.getName();

    Vector2 position;

    Icicle(Vector2 position)
    {
      this.position=position;
    }

    void render(ShapeRenderer renderer)
    {
        renderer.setColor(Constants.ICICLE_COLOR);

        renderer.set(ShapeRenderer.ShapeType.Filled);

        renderer.triangle(
                position.x, position.y,
                position.x - Constants.ICICLES_WIDTH / 2, position.y + Constants.ICICLES_HEIGHT,
                position.x + Constants.ICICLES_WIDTH / 2, position.y + Constants.ICICLES_HEIGHT
        );




    }
}
