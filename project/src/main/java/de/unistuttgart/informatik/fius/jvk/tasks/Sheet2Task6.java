package de.unistuttgart.informatik.fius.jvk.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;

public class Sheet2Task6 implements Task{
    private PlayfieldModifier pm;
    private SubTask subTask;

    public Sheet2Task6(SubTask subTask) {
        this.subTask = subTask;
    }

    private void subTaskB(Neo neo){
        /*
         TODO
        */
    }

    private void subTaskC(Neo neo){
        /*
         TODO
        */
    }

    @Override
    public void run(Simulation sim) {
        this.pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        neo.setCoinsInWallet(100);
        this.pm.placeEntityAt(neo, new Position(0, 0));
        buildEnvironment();

        if(this.subTask == SubTask.B)
            subTaskB(neo);
        if(this.subTask == SubTask.C)
            subTaskC(neo);
    }

    private void buildEnvironment(){
        buildRectangle(-1, -1, 10, 10);
    }

    private void buildRectangle(int x, int y, int width, int height){
        if(width <= 2 || height <= 2)
            throw new IllegalArgumentException();
        buildHorizontalWalls(y, x, x+width);
        buildHorizontalWalls(y+height, x, x+width);
        buildVerticalWalls(x, y+1, y+height-1);
        buildVerticalWalls(x+width, y+1, y+height-1);
    }

    private void buildHorizontalWalls(int y, int x1, int x2) throws IllegalArgumentException{
        if(x2 < x1)
            throw new IllegalArgumentException();
        for(int x = x1; x <= x2; x++)
            this.pm.placeEntityAt(new Wall(), new Position(x, y));
    }

    private void buildVerticalWalls(int x, int y1, int y2) throws IllegalArgumentException{
        if(y2 < y1)
            throw new IllegalArgumentException();
        for(int y = y1; y <= y2; y++)
            this.pm.placeEntityAt(new Wall(), new Position(x, y));
    }
}