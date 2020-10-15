package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Line;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;

public class Sheet2Task3 implements Task{
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo player = new Neo();
        pm.placeEntityAt(player, new Position(0, 0));
        buildEnvironment(pm);
        
        //implement subtask here
        player.move();
        player.move();
        player.move();
        player.move();
        player.move();
        player.move();
        player.move();
        player.move();
        player.move();
    }

    private void buildEnvironment(PlayfieldModifier pm){
        pm.placeEntityAt(new PhoneBooth(), new Position(10, 0));
        pm.placeEntityAtEachPosition(() -> new Wall(), new Line(new Position(-1, -2), new Position(11, -2)));
        pm.placeEntityAtEachPosition(() -> new Wall(), new Line(new Position(0, 2), new Position(3, 2)));
        pm.placeEntityAtEachPosition(() -> new Wall(), new Line(new Position(7, 2), new Position(10, 2)));
        pm.placeEntityAtEachPosition(() -> new Wall(), new Line(new Position(-1, -1), new Position(-1, 2)));
        pm.placeEntityAtEachPosition(() -> new Wall(), new Line(new Position(11, -1), new Position(11, 2)));
        
        //Place Wall between the player and the phone booth
        pm.placeEntityAt(new Wall(), new Position(5, 0));
    }
}