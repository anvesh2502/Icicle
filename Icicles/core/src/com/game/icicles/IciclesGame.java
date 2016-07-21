package com.game.icicles;

import com.badlogic.gdx.Game;
import com.game.icicles.Constants;
import com.game.icicles.IciclesScreen;
import com.game.icicles.Constants.Difficulty;


public class IciclesGame extends Game {

    @Override
    public void create() {
        showDifficultyScreen();
    }


    public void showDifficultyScreen() {
        // TODO: Show the difficulty screen
        setScreen(new com.game.icicles.DifficultyScreen(this));
    }

    public void showIciclesScreen(Constants.Difficulty difficulty) {
        // TODO: Show the Icicles screen with the appropriate difficulty
        setScreen(new IciclesScreen(this, difficulty));
    }
}
