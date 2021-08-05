package de.entjic.invasion.game;

import de.entjic.invasion.Invasion;
import de.entjic.invasion.game.timer.Timer;
import de.entjic.invasion.game.wave.WaveSpawner;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class Game {
    private GameState gameState;
    private Trigger trigger;
    private WaveSpawner waveSpawner;
    private int taskID;
    private Timer gameTimer;

    public Game() {
        gameState = GameState.INACTIVE;
    }

    public void start(Location trigger) {
        gameState = GameState.ACTIVE;
        this.trigger = new Trigger(trigger, this);
        init();
        createTickMachine();
    }

    public void pause() {
        gameState = GameState.PAUSED;
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public void resume() {
        gameState = GameState.ACTIVE;
        createTickMachine();
    }

    public void reset() {
        gameState = GameState.INACTIVE;
        trigger = null;
        Bukkit.getScheduler().cancelTask(taskID);
    }

    public GameState getGameState() {
        return gameState;
    }

    private void init() {
        waveSpawner = new WaveSpawner(trigger.getLocation());
        gameTimer = new Timer();
        initWorld();
    }

    private void createTickMachine() {
        taskID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Invasion.getProvidingPlugin(Invasion.class), () -> {
            gameTimer.update(0);
            int gameTick = gameTimer.getTime();
            update(gameTick);
            render(gameTick);
        }, 0L, 20L);
    }

    private void update(int gameTick) {
        waveSpawner.update(gameTick);
        trigger.update(gameTick);
    }

    private void render(int gameTick) {
        trigger.render(gameTick);
    }

    private void initWorld() {
        Bukkit.getWorld("world").getEntities().stream().filter(e -> ! e.getType().equals(EntityType.PLAYER)).forEach(Entity::remove);
    }
}
