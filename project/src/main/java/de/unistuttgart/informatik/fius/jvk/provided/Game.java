package de.unistuttgart.informatik.fius.jvk.provided;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationBuilder;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.entity.EntityTypeRegistry;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.ui.GameWindow;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;
import de.unistuttgart.informatik.fius.icge.ui.WindowBuilder;
import de.unistuttgart.informatik.fius.jvk.Texture;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Pill;

/**
 * A helper to start a Simulation with a GameWindow.
 */
public class Game {

    private final GameWindow window;
    private final Simulation simulation;
    private final Task task;

    /**
     * Create a new {@link Simulation} and {@link GameWindow} with the given window title.
     *
     * @param windowTitle the title of the game window
     */
    public Game(String windowTitle) {
        this(windowTitle, null, null);
    }

    /**
     * Create a new {@link Simulation} and {@link GameWindow} with the given window title.
     * <p>
     * The task is run when the game is started with the {@link #run()} command of the game.
     *
     * @param windowTitle the title of the game window
     * @param task the task to run on game start
     */
    public Game(String windowTitle, Task task) {
        this(windowTitle, task, null);
    }

    /**
     * Create a new {@link Simulation} with a {@link TaskVerifier} and {@link GameWindow} with the given window title.
     *
     * @param windowTitle the title of the game window
     * @param verifier the verifier that checks if a task was solved correctly
     */
    public Game(String windowTitle, TaskVerifier verifier) {
        this(windowTitle, null, verifier);
    }

    /**
     * Create a new {@link Simulation} with a {@link TaskVerifier} and {@link GameWindow} with the given window title.
     * <p>
     * The task is run when the game is started with the {@link #run()} command of the game.
     *
     * @param windowTitle the title of the game window
     * @param task the task to run on game start
     * @param verifier the verifier that checks if a task was solved correctly
     */
    public Game(String windowTitle, Task task, TaskVerifier verifier) {
        final WindowBuilder wb = new WindowBuilder();
        wb.setTitle(windowTitle);
        wb.setGraphicsSettings(false, true);
        wb.buildWindow();
        this.window = wb.getBuiltWindow();
        this.registerTextures(this.window);

        final SimulationBuilder sb = new SimulationBuilder();
        if (verifier != null) {
            sb.setTaskVerifier(verifier);
        }
        sb.buildSimulation();
        this.simulation = sb.getBuiltSimulation();

        this.simulation.attachToWindow(this.window, true);
        this.prepareEntityTypes(this.simulation);

        this.task = task;
    }

    /**
     * Get the created {@link GameWindow} object.
     *
     * @return the {@link GameWindow} object
     */
    public GameWindow getGameWindow() {
        return this.window;
    }

    /**
     * Get the created {@link Simulation} object.
     *
     * @return the {@link Simulation} object.
     */
    public Simulation getSimulation() {
        return this.simulation;
    }

    /**
     * Show the game window and start the given Task.
     */
    public void run() {
        this.window.start();
        if (this.task != null) {
            this.simulation.runTask(this.task);
        }
    }

    /**
     * Register all textures in the texture registry of the game window.
     *
     * @param window the window to get the texture registry from
     */
    private void registerTextures(GameWindow window) {
        TextureRegistry tr = window.getTextureRegistry();
        for (Texture texture: Texture.values()) {
            texture.load(tr);
        }
    }

    /**
     * Register some entity types to be spawnable in the ui.
     *
     * @param simulation the simulation to get the entitytype registry from
     */
    private void prepareEntityTypes(Simulation simulation) {
        EntityTypeRegistry etr = simulation.getEntityTypeRegistry();
        etr.registerEntityType("Wall", Texture.WALL.getHandle(), Wall.class);
        etr.registerEntityType("Coin", Texture.COIN.getHandle(), Coin.class);
        etr.registerEntityType("Red Pill", Texture.REDPILL.getHandle(), () -> new Pill(Color.RED));
        etr.registerEntityType("Blue Pill", Texture.BLUEPILL.getHandle(), () -> new Pill(Color.BLUE));
    }

}