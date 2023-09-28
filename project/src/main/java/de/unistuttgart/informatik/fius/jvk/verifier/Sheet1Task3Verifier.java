package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;


public class Sheet1Task3Verifier implements TaskVerifier {

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("e) Spawn 5 Coins", "Spawn at least 5 coins.");

    private ActionLog actionLog;

    public Sheet1Task3Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        this.task = new BasicTaskInformation("Sheet 1 Task 5", "Learn how to use the Playfield modifier.", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
    }

    @Override
    public void verify() {
        System.out.println("Verify");
        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);

        List<Position> coinPositions = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Coin))
            .map((action) -> action.getPosition())
            .sorted((a, b) -> {
                if (a.getY() < b.getY()) return -1;
                if (a.getY() == b.getY()) {
                    if (a.getX() < b.getX()) return -1;
                    if (a.getX() == b.getX()) return 0;
                }
                return 1;
            })
            .collect(Collectors.toList());

        // subtask b)
        int nrOfCoinsSpawned = coinPositions.size();
        if (nrOfCoinsSpawned >= 5) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);

        if (subTasks.stream().allMatch(subTask -> subTask.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        this.task = this.task.updateSubTasks(subTasks);
    }

    /**
     * Check if a field with at least {@code nrOfCoins} exists.
     */
    private boolean checkMultiCoinsField(List<Position> coinPositions, int nrOfCoins) {
        Position currentPos = null;
        int currentCount = 0;

        Iterator<Position> positions = coinPositions.iterator();

        while (positions.hasNext()) {
            Position p = positions.next();
            if (currentPos == null || !p.equals(currentPos)) {
                if (currentCount == nrOfCoins) { // found field with right coincount
                    return true;
                }
                currentPos = p;
                currentCount = 0;
            }
            currentCount++;
            if (!positions.hasNext()) { // special check for end of list
                if (currentCount == nrOfCoins) { // found field with right coincount
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Count the number of horizontal lines of length {@code lineLength}.
     */
    private int countLines(List<Position> coinPositions, int lineLength) {
        if(coinPositions.isEmpty()){
            return 0;
        }
        int currentX = coinPositions.get(0).getX();
        int currentY = coinPositions.get(0).getY();
        int rowCount = 0;
        int currentLength = 0;

        int lineCount = 0;

        Iterator<Position> positions = coinPositions.iterator();

        while (positions.hasNext()) {
            Position p = positions.next();
            if (currentY == p.getY()) {
                if (currentX + currentLength == p.getX()) {
                    currentLength++;
                    if (!positions.hasNext()) {
                        // special check for end of Iterator
                        if (currentLength == lineLength) {
                            // only count lines of the exact length specified
                            lineCount++;
                        }
                    }
                } else {
                    if (currentLength == lineLength) {
                        // only count lines of the exact length specified
                        lineCount++;
                    }
                    currentX = p.getX();
                    currentLength = 1;
                }
            } else {
                if (currentLength == lineLength) {
                    // only count lines of the exact length specified
                    lineCount++;
                }
                // start new row
                currentY = p.getY();
                currentX = p.getX();
                currentLength = 1;
            }
        }

        return lineCount;
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }



}
