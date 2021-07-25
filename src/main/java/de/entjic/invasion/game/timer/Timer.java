package de.entjic.invasion.game.timer;

import de.entjic.invasion.game.GameObject;

public class Timer implements GameObject {
    private int time;

    public Timer() {
        time = 0;
    }


    @Override
    public void update(int i) {
        time++;
    }


    @Override
    public void render(int i) {

    }

    public int getTime() {
        return time;
    }

}
