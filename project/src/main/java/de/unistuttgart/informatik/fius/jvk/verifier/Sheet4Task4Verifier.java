package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationClock;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
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


public class Sheet4Task4Verifier implements TaskVerifier {
    
    private final String taskDescription = "Reach the other wall with all three Neos at the same time!";
    private BasicTaskInformation task = new BasicTaskInformation("Race", this.taskDescription);
    
    private ActionLog  actionLog;
    private SimulationClock clock;
    
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.clock = sim.getSimulationClock();
    }
    

    
    @Override
    public void verify() {
        int GoalCoordinate = 20;

        List<Long> goalReachedOn = this.actionLog.getActionsOfType(EntityStepAction.class, true).stream()
            .filter((action) -> action.getEntity() instanceof Neo)
            .filter((action) -> action.to().getX() == 20)
            .map((action) -> action.getTickNumber())
            .collect(Collectors.toList());

        if (goalReachedOn.size() == 0) {
            return; // no neo reached goal
        }
        if (goalReachedOn.size() == 1) {
            this.task = this.task.updateDescription(this.taskDescription + "\nOne Neo has reached the goal.");
        }
        if (goalReachedOn.size() > 1) {
            String reachedGoal = goalReachedOn.size() + " Neos have reached the goal.";
            int differentArrivalTimes = new HashSet<Long>(goalReachedOn).size() - 1;
            int arrivedTogether = goalReachedOn.size() - differentArrivalTimes;
            if (arrivedTogether == 1) {
                this.task = this.task.updateDescription(this.taskDescription + "\n" + reachedGoal + " – no Neo arrived at the same time");
            } else {
                this.task = this.task.updateDescription(this.taskDescription + "\n" + reachedGoal + " – " + arrivedTogether + " Neos arrived at the same time.");
            }
            if (differentArrivalTimes == 0 && goalReachedOn.size() == 3) {
                this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
    }

    private boolean checkNeoF(List<EntityDropAction> drops) {
        for (int i = 0; i < 6; i++) {
            Position pos = new Position(i, 2);
            long dropCount = drops.stream().filter(drop -> drop.getDroppedEntityPosition().equals(pos)).count();
            if (dropCount != i) return false;
        }
        return true;
    }

    private boolean checkNeoG(List<EntityCollectAction> collects) {
        for (int i = 0; i < 10; i++) {
            Position pos = new Position(i+1, 4);
            long collectedCount = collects.stream().filter(drop -> drop.getCollectedEntityPosition().equals(pos)).count();
            if (collectedCount > 5) return false;
        }
        return true;
    }
    
   
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
