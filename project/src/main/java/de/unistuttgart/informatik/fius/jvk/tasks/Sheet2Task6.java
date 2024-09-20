package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;

public class Sheet2Task6 implements Task{
    private PlayfieldModifier pm;

    public Sheet2Task6() {
    }

    @Override
    public void run(Simulation sim) {
        this.pm = new PlayfieldModifier(sim.getPlayfield());
        Totoro totoro = new Totoro();
        this.pm.placeEntityAt(totoro, new Position(0, 0));
        buildEnvironment();

        //implement subtasks here
    }

    private void buildEnvironment(){
        pm.placeEntityAtEachPosition(new BushFactory(), new Rectangle(new Position(-1, -1), new Position(9, 9)));
    }
}
