package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Nut;


/**
 * TODO: Description
 * 
 * @author Jannik Graef
 */
public class OptionalLabyrinthTaskVerifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskB = new BasicTaskInformation(
            "b) complete the Labrinth", "complete the Labrinth and collect the nut at the end."
    );
    private Simulation           sim;
    
    /**
     * 
     */
    public OptionalLabyrinthTaskVerifier() {
        
        //System.gc();
        System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        this.task = new BasicTaskInformation("(Optional) Labyrinth Task", "", subTasks);
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.sim = sim;
    }
    
    @Override
    public void verify() {
        
        if (getNumberOfCoinsAtPosition(8, 2) == 0) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        
        this.task = this.task.updateSubTasks(subTasks);
    }
    
    private int getNumberOfCoinsAtPosition(int x, int y) {
        return getNumberOfCoinsAtPosition(new Position(x, y));
    }
    
    private int getNumberOfCoinsAtPosition(Position pos) {
        return this.sim.getPlayfield().getEntitiesOfTypeAt(pos, Nut.class, true).size();
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
