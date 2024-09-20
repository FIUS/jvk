package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Arrays;
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
public class Sheet3Task3Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskB = new BasicTaskInformation("b)", "Walk the pattern on the exercise sheet.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c)", "Walk the patern three times.");
    private BasicTaskInformation taskG = new BasicTaskInformation(
            "g)", "execute dropFourNutsAndTurnLeft after movePattern in the Loop Body."
    );
    private BasicTaskInformation taskI = new BasicTaskInformation(
            "i)", "See exercise sheet."
    );
    private ActionLog            actionLog;
    private Simulation           sim;
    
    public Sheet3Task3Verifier() {
        
        //System.gc();
        //System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskG);
        subTasks.add(this.taskI);
        this.task = new BasicTaskInformation("Sheet 3 Task 3", "Operations.", subTasks);
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
        List<EntityStepAction> stepActions = this.actionLog.getActionsOfType(EntityStepAction.class, true);
        List<List<Position>> stepPositions = new ArrayList<>();
        stepActions.forEach(stepAction -> stepPositions.add(Arrays.asList(new Position[] { stepAction.from(), stepAction.to() })));
        
        //Pattern
        List<List<Position>> listB = new ArrayList<List<Position>>();
        listB.add(Arrays.asList(new Position[] { new Position(0, 0), new Position(1, 0) }));
        listB.add(Arrays.asList(new Position[] { new Position(1, 0), new Position(2, 0) }));
        listB.add(Arrays.asList(new Position[] { new Position(2, 0), new Position(2, 1) }));
        listB.add(Arrays.asList(new Position[] { new Position(2, 1), new Position(3, 1) }));
        listB.add(Arrays.asList(new Position[] { new Position(3, 1), new Position(4, 1) }));
        listB.add(Arrays.asList(new Position[] { new Position(4, 1), new Position(5, 1) }));
        listB.add(Arrays.asList(new Position[] { new Position(5, 1), new Position(5, 0) }));
        listB.add(Arrays.asList(new Position[] { new Position(5, 0), new Position(5, -1) }));
        listB.add(Arrays.asList(new Position[] { new Position(5, -1), new Position(6, -1) }));
        listB.add(Arrays.asList(new Position[] { new Position(6, -1), new Position(7, -1) }));
        listB.add(Arrays.asList(new Position[] { new Position(7, -1), new Position(7, 0) }));
        
        if (stepPositions.containsAll(listB)) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<List<Position>> listC = listB;
        listC.add(Arrays.asList(new Position[] { new Position(0 + 7, 0), new Position(1 + 7, 0) }));
        listC.add(Arrays.asList(new Position[] { new Position(1 + 7, 0), new Position(2 + 7, 0) }));
        listC.add(Arrays.asList(new Position[] { new Position(2 + 7, 0), new Position(2 + 7, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(2 + 7, 1), new Position(3 + 7, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(3 + 7, 1), new Position(4 + 7, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(4 + 7, 1), new Position(5 + 7, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(5 + 7, 1), new Position(5 + 7, 0) }));
        listC.add(Arrays.asList(new Position[] { new Position(5 + 7, 0), new Position(5 + 7, -1) }));
        listC.add(Arrays.asList(new Position[] { new Position(5 + 7, -1), new Position(6 + 7, -1) }));
        listC.add(Arrays.asList(new Position[] { new Position(6 + 7, -1), new Position(7 + 7, -1) }));
        listC.add(Arrays.asList(new Position[] { new Position(7 + 7, -1), new Position(7 + 7, 0) }));
        
        listC.add(Arrays.asList(new Position[] { new Position(0 + 14, 0), new Position(1 + 14, 0) }));
        listC.add(Arrays.asList(new Position[] { new Position(1 + 14, 0), new Position(2 + 14, 0) }));
        listC.add(Arrays.asList(new Position[] { new Position(2 + 14, 0), new Position(2 + 14, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(2 + 14, 1), new Position(3 + 14, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(3 + 14, 1), new Position(4 + 14, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(4 + 14, 1), new Position(5 + 14, 1) }));
        listC.add(Arrays.asList(new Position[] { new Position(5 + 14, 1), new Position(5 + 14, 0) }));
        listC.add(Arrays.asList(new Position[] { new Position(5 + 14, 0), new Position(5 + 14, -1) }));
        listC.add(Arrays.asList(new Position[] { new Position(5 + 14, -1), new Position(6 + 14, -1) }));
        listC.add(Arrays.asList(new Position[] { new Position(6 + 14, -1), new Position(7 + 14, -1) }));
        listC.add(Arrays.asList(new Position[] { new Position(7 + 14, -1), new Position(7 + 14, 0) }));
        
        if (stepPositions.containsAll(listC)) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        if (
            (getNumberOfCoinsAtPosition(7, 0) == 4) && (getNumberOfCoinsAtPosition(7, -7) == 4) && (getNumberOfCoinsAtPosition(0, -7) == 4)
        ) {
            this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<List<Position>> listI = new ArrayList<List<Position>>();
        for (int i = 0; i < 7; i++) {
            listI.add(Arrays.asList(new Position[] { new Position(0 + i, 0), new Position(1 + i, 0) }));
        }
        for (int i = 0; i < 7; i++) {
            listI.add(Arrays.asList(new Position[] { new Position(7, 0 - i), new Position(7, -1 - i) }));
        }
        for (int i = 0; i < 7; i++) {
            listI.add(Arrays.asList(new Position[] { new Position(7 - i, -7), new Position(6 - i, -7) }));
        }
        if (stepPositions.containsAll(listI)) {
            this.taskI = this.taskI.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskG);
        subTasks.add(this.taskI);
        this.task = this.task.updateSubTasks(subTasks);
        
    }
    
    private int getNumberOfCoinsAtPosition(int x, int y) {
        return getNumberOfCoinsAtPosition(new Position(x, y));
    }
    
    private int getNumberOfCoinsAtPosition(Position pos) {
        return this.sim.getPlayfield().getEntitiesOfTypeAt(pos, Coin.class, true).size();
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
