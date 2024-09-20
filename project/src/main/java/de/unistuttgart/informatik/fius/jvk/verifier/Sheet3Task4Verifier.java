package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;


/**
 *
 * @author Jannik Graef
 */
public class Sheet3Task4Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    Integer taskERuns = 7;
    private BasicTaskInformation taskE = new BasicTaskInformation(
            "e)", "Walk a spiral and turn at least " + taskERuns.toString() +" times.", TaskVerificationStatus.UNDECIDED
    );
    private ActionLog            actionLog;
    private Simulation           sim;
    
    public Sheet3Task4Verifier() {
        
        //System.gc();
        //System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskE);
        this.task = new BasicTaskInformation("Sheet 3 Task 4", "Operations.", subTasks);
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.sim = sim;
        this.actionLog = sim.getActionLog();
        //System.out.println("test2");
    }
    
    @Override
    public void verify() {
        this.actionLog = this.sim.getActionLog();
        List<EntityStepAction> Actions = this.actionLog.getActionsOfType(EntityStepAction.class, true);
        List<Position> expectedPositions = new ArrayList<>();
        
        //caluclate all expect positions
        expectedPositions.add(new Position(0, 0));
        for (Integer runs = 1; runs <= taskERuns; runs++) {
            Position delta = new Position(0, 0);
            switch ((runs - 1) % 4) {
                case 0:
                    delta = new Position(1, 0);
                    break;
                
                case 1:
                    delta = new Position(0, 1);
                    break;
                
                case 2:
                    delta = new Position(-1, 0);
                    break;
                
                case 3:
                    delta = new Position(0, -1);
                    break;
            }
            for (int i = 0; i < runs; i++) {
                Position lastPosition = expectedPositions.get(expectedPositions.size() - 1);
                expectedPositions.add(new Position(lastPosition.getX() + delta.getX(), lastPosition.getY() + delta.getY()));
            }
        }
        //actually test task E
        if (Actions.size() >= expectedPositions.size()) {
            for (int i = 0; i < expectedPositions.size(); i++) {
                if (!expectedPositions.get(i).equals(Actions.get(i).from())) {
                    this.taskE = this.taskE.updateStatus(TaskVerificationStatus.FAILED);
                    break;
                }
            }
            if (this.taskE.getTaskStatus() != TaskVerificationStatus.FAILED) {
                this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        else{
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.UNDECIDED);
        }
        
        //Submit results
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskE);
        this.task = this.task.updateSubTasks(subTasks);
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
