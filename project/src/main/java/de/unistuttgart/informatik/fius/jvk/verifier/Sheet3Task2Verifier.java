package de.unistuttgart.informatik.fius.jvk.verifier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Line;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;
import de.unistuttgart.informatik.fius.jvk.tasks.Sheet3Task1;


public class Sheet3Task2Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskC = new BasicTaskInformation("c)", "Walk until you find a coin.");
    private BasicTaskInformation taskD = new BasicTaskInformation(
            "d)", "when on a field with exactly one coin, pick up the coin and turn right."
    );
    private BasicTaskInformation taskE = new BasicTaskInformation(
            "e)", "when on a field with more than one coin, pick up a coin and turn right."
    );
    private BasicTaskInformation taskF = new BasicTaskInformation("f)", "Turn around when facing a wall and not standing on a coin.");
    private BasicTaskInformation taskG = new BasicTaskInformation("g)", "Walk until you have collected 20 coins or walked 10 steps");
    private BasicTaskInformation taskH = new BasicTaskInformation("h)", "complete all other subtasks.");
    
    private ActionLog  actionLog;
    private Simulation sim;
    
    public Sheet3Task2Verifier() {
        
        //System.gc();
        //System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);
        this.task = new BasicTaskInformation("Sheet 3 Task 2", "If-Conditions.", subTasks);
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
        
        //c)
        if (
            stepPositions.contains(Arrays.asList(new Position[] { new Position(3, 0), new Position(4, 0) }))
                    && !stepPositions.contains(Arrays.asList(new Position[] { new Position(4, 0), new Position(5, 0) }))
        ) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        //d)
        if (
            (getNumberOfCoinsAtPosition(4, 0) == 0) && ((this.actionLog.getActionsOfType(EntityTurnAction.class, true).size() == 1)
                    || (stepPositions.contains(Arrays.asList(new Position[] { new Position(4, 0), new Position(4, 1) }))))
        ) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        //e)
        if (
            (getNumberOfCoinsAtPosition(4, 4) == 0) && ((this.actionLog.getActionsOfType(EntityTurnAction.class, true).size() == 4)
                    || (stepPositions.contains(Arrays.asList(new Position[] { new Position(3, 4), new Position(4, 4) }))))
        ) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        //f)
        if (
            (stepPositions.contains(Arrays.asList(new Position[] { new Position(12, 5), new Position(12, 6) })))
                    && (stepPositions.contains(Arrays.asList(new Position[] { new Position(12, 6), new Position(12, 5) })))
        ) {
            this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        //g)
        if (
            ((this.actionLog.getActionsOfType(EntityCollectAction.class, true).size() == 10)
                    || (this.actionLog.getActionsOfType(EntityStepAction.class, true).size() == 20))
        ) {
            this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        if (
            (this.taskA.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))
                    && (this.taskC.getTaskStatus().equals( TaskVerificationStatus.SUCCESSFUL))
                    && (this.taskD.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))
                    && (this.taskE.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))
                    && (this.taskF.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))
                    && (this.taskG.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))
        ) {
            this.taskH = this.taskH.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);
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
