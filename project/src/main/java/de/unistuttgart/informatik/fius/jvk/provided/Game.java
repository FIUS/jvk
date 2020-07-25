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
import de.unistuttgart.informatik.fius.jvk.tasks.PlaygroundTask;
import de.unistuttgart.informatik.fius.jvk.verifier.PlaygroundTaskVerifier;

public class Game {

    private final GameWindow window;
    private final Simulation simulation;
    private final Task task;

    public Game(String windowTitle) {
        this(windowTitle, new PlaygroundTask());
    }

    public Game(String windowTitle, Task task) {
        this(windowTitle, task, new PlaygroundTaskVerifier());
    }

    public Game(String windowTitle, TaskVerifier verifier) {
        this(windowTitle, new PlaygroundTask(), verifier);
    }

    public Game(String windowTitle, Task task, TaskVerifier verifier) {
        final WindowBuilder wb = new WindowBuilder();
        wb.setTitle(windowTitle);
        wb.setGraphicsSettings(false, true);
        wb.buildWindow();
        this.window = wb.getBuiltWindow();
        this.registerTextures(this.window);

        final SimulationBuilder sb = new SimulationBuilder();
        sb.setTaskVerifier(verifier);
        sb.buildSimulation();
        this.simulation = sb.getBuiltSimulation();
        this.prepareEntityTypes(simulation);

        this.simulation.attachToWindow(this.window, true);

        this.task = task;
    }

    public void run() {
        this.window.start();
        if (this.task != null) {
            this.simulation.runTask(this.task);
        }
    }

    public GameWindow getGameWindow() {
        return this.window;
    }

    public Simulation getSimulation() {
        return this.simulation;
    }

    private void registerTextures(GameWindow window) {
        TextureRegistry tr = this.window.getTextureRegistry();
        for (Texture texture: Texture.values()) {
            texture.load(tr);
        }
    }

    private static void prepareEntityTypes(Simulation simulation) {
        EntityTypeRegistry etr = simulation.getEntityTypeRegistry();
        etr.registerEntityType("Wall", Texture.WALL.getHandle(), Wall.class);
        etr.registerEntityType("Coin", Texture.COIN.getHandle(), Coin.class);
        etr.registerEntityType("Red Pill", Texture.REDPILL.getHandle(), () -> new Pill(Color.RED));
        etr.registerEntityType("Blue Pill", Texture.BLUEPILL.getHandle(), () -> new Pill(Color.BLUE));
    }

}