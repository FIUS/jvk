package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.MovableEntity;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;


public class Sheet2Task1Verifier implements TaskVerifier {

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select This Task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Spawn Totoro", "Spawn Totoro on the field.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Move Totoro", "Move and turn Totoro.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Turning Counterclockwise", "Turn Totoro counterclockwise.");
    private BasicTaskInformation taskE = new BasicTaskInformation("e) Follow the Map", "Follow the instructions from the task sheet.");

    private List<String> mapMoves;

    private ActionLog actionLog;

    public Sheet2Task1Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        this.task = new BasicTaskInformation("Sheet 2 Task 1", "Learn how to use Totoro.", subTasks);

        List<String> mapMoves = new ArrayList<>();
        mapMoves.add("move");
        mapMoves.add("move");
        mapMoves.add("turn");
        mapMoves.add("move");
        mapMoves.add("turn");
        mapMoves.add("turn");
        mapMoves.add("turn");
        mapMoves.add("move");
        mapMoves.add("move");
        mapMoves.add("move");
        mapMoves.add("move");
        mapMoves.add("turn");
        mapMoves.add("move");
        this.mapMoves = mapMoves;
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
    }

    @Override
    public void verify() {
        System.out.println("Verify");
        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);

        Optional<Entity> maybePlayer = spawnActions.stream()
            .map((action) -> action.getEntity())
            .filter((entity) -> (entity instanceof MovableEntity))
            .findFirst();

        if (maybePlayer.isEmpty()) {
            return; // no subtask needs to change if no player is spawned!
        }

        Entity player = maybePlayer.get();

        Comparator<Long> nat = Comparator.naturalOrder();

        List<String> playerActions = this.actionLog.getAllActionsOfEntity(player).stream()
            .filter(action -> (action instanceof EntityMoveAction || action instanceof EntityTurnAction))
            .sorted((a,b) -> nat.compare(a.getTickNumber(), b.getTickNumber()))
            .map(action -> {
                if (action instanceof EntityMoveAction) return "move";
                if (action instanceof EntityTurnAction) return "turn";
                return "false";
            })
            .collect(Collectors.toList());

        long stepsWalked = playerActions.stream().filter(action -> action.equals("move")).count();
        long turns = playerActions.stream().filter(action -> action.equals("turn")).count();
        boolean invalidMoves = playerActions.stream().anyMatch(action -> action.equals("false")); // should never happen theoretically...

        // subtask b) Player is spawned here
        this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL); 

        // subtask c)
        if (stepsWalked > 1 && turns > 0) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        int turnCount = this.countTurns(playerActions);
        
        // subtask d)
        if (turnCount > 2) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        // subtask e)
        int correctMoves = checkAllMoves(playerActions);
        if (correctMoves > 0) {
            String hint = correctMoves + " correct move(s) or turn(s) out of " + this.mapMoves.size();
            this.taskE = this.taskE.updateDescription("Follow the instructions from the task sheet.\n" + hint);
        }
        if (correctMoves == this.mapMoves.size()) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);

        if (subTasks.stream().allMatch(subTask -> subTask.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        this.task = this.task.updateSubTasks(subTasks);
    }

    private int countTurns(List<String> playerActions) {
        int currentTurnCount = 0;
        int maxTurnCount = 0;
        for (String action : playerActions) {
            if (action.equals("turn")) currentTurnCount++;
            else {
                if (currentTurnCount > maxTurnCount) {
                    maxTurnCount = currentTurnCount;
                }
                currentTurnCount = 0;
            }
        }
        if (currentTurnCount > maxTurnCount) return currentTurnCount;
        return maxTurnCount;
    }

    private int checkAllMoves(List<String> playerActions) {
        int correctMoves = 0;
        for (int i = 0; i < this.mapMoves.size() && i < playerActions.size(); i++) {
            if (!this.mapMoves.get(i).equals(playerActions.get(i))) break;
            correctMoves++;
        }
        return correctMoves;
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }



}
