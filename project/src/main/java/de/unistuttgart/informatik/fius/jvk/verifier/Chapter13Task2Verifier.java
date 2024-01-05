package de.unistuttgart.informatik.fius.jvk.verifier;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Line;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Shape;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.UnionShape;

import java.util.*;
import java.util.stream.Collectors;


public class Chapter13Task2Verifier implements TaskVerifier {

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Walk to the opposite wall and then drop one coin.", "Walk to the opposite wall and then drop one coin.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Walk to the wall or until you stand on a field with exactly two coins.", "Walk to the wall or until you stand on a field with exactly two coins.");
    private ActionLog actionLog;
    private Simulation sim;

    private Position dropPosition = new Position(9, 0);

    public Chapter13Task2Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        this.task = new BasicTaskInformation("Chapter 13 Task 2", "While Loops and break", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {


        if (this.sim.getPlayfield().getEntitiesOfTypeAt(new Position(14, 1), Coin.class, true).isEmpty()) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        Position collectionPosition = new Position(0, 0);
        for (EntityCollectAction action : actionLog.getActionsOfType(EntityCollectAction.class, true)) {
            if ((action.getCollectedEntityPosition().getY() == 3)) {
                collectionPosition = action.getCollectedEntityPosition();
            }
        }

        if (((sim.getPlayfield().getEntitiesOfTypeAt(collectionPosition, Neo.class, true).size() == 1) || this.sim.getPlayfield().getEntitiesOfTypeAt(new Position(14, 3), Coin.class, true).isEmpty()) && (actionLog.getActionsOfType(EntityCollectAction.class, true).size() <= 2)) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }


        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);

        this.task = this.task.updateSubTasks(subTasks);
    }


    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }


}
