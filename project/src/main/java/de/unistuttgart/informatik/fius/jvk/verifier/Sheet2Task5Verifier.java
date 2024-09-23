package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Bush;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Nut;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;


public class Sheet2Task5Verifier implements TaskVerifier {

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Collect all nuts", "Collect all nuts.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Place all nuts in front of the right bush.", "Place all nuts in front of the right bush.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Draw a line", "Put down exactly one nut on each field in the middle row.");
    private BasicTaskInformation taskE = new BasicTaskInformation("e) Draw a thick line", "Put down exactly three nuts on each field in the middle row.");
    private BasicTaskInformation taskF = new BasicTaskInformation("f) Draw a dotted line", "Put down exactly one nut every second step in the middle row. The first field should be empty.");
    private BasicTaskInformation taskG = new BasicTaskInformation("g) Fill out the Field", "Place a nut in all reachable Fields.");

    private ActionLog actionLog;
    private Simulation sim;

    private Position dropPosition = new Position(9,0);

    public Sheet2Task5Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        this.task = new BasicTaskInformation("Sheet 2 Task 5", "While Schleifen", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {
        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);

        long nrOfNuts = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .map((action) -> action.getEntity())
            .distinct()
            .count();

        boolean allCollected = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .map((action) -> action.getEntity())
            .map((nut) -> this.actionLog.getActionsOfTypeOfEntity(nut, EntityDespawnAction.class, true))
            .allMatch((despawns) -> despawns.size() == 1);

        boolean allDroppedOnPosition = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .map((action) -> action.getEntity())
            .map((nut) -> this.actionLog.getActionsOfTypeOfEntity(nut, EntitySpawnAction.class, true))
            .allMatch((spawns) -> spawns.stream().anyMatch(spawn -> spawn.getPosition().equals(this.dropPosition)));

        Set<Position> linePositionsToFill = new HashSet<>();
        for (Position pos : new Line(new Position(0, 0), new Position(9, 0))) {
            linePositionsToFill.add(pos);
        }

        Comparator<Integer> nat = Comparator.naturalOrder();
        List<Position> coinDrops = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Nut))
            .filter((action) -> (action.getTickNumber() > 0))
            .map((action) -> action.getPosition())
            .sorted((a,b) -> nat.compare(a.getX(), b.getX()))
            .collect(Collectors.toList());
        
        Set<Position> wallsTop = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Bush))
            .map((action) -> action.getPosition())
            .filter((pos) -> (pos.getY() == -1 && pos.getX() < 10 && pos.getX() > -1))
            .collect(Collectors.toSet());
        
        Set<Position> wallsBottom = spawnActions.stream()
            .filter((action) -> (action.getEntity() instanceof Bush))
            .map((action) -> action.getPosition())
            .filter((pos) -> (pos.getY() == 1 && pos.getX() < 10 && pos.getX() > -1))
            .collect(Collectors.toSet());

        if(allCollected) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if(allCollected && allDroppedOnPosition) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        boolean droppedInLine = checkAllDroppedOnShape(coinDrops, linePositionsToFill);
        if(allCollected && droppedInLine) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        boolean droppedThreeInLine = checkAllDroppedThreeInLine(coinDrops, linePositionsToFill);
        if(allCollected && droppedThreeInLine) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        boolean droppedOnLineOnEven = checkAllDroppedInLineOnEven(coinDrops, linePositionsToFill);
        if(allCollected && droppedOnLineOnEven) {
            this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        Shape allInners = new UnionShape(
                new Line(new Position(0, -1), new Position(9, -1)),
                new Line(new Position(0, 0), new Position(9, 0)),
                new Line(new Position(0, 1), new Position(9, 1))
            );
        Set<Position> allNonWalls = new HashSet<>();
        for (Position pos : allInners) {
            allNonWalls.add(pos);
        }
        allNonWalls.removeAll(wallsTop);
        allNonWalls.removeAll(wallsBottom);
        
        boolean coinsOnAllNonWalls = checkAllDroppedOnShape(coinDrops, allNonWalls);
        if(allCollected && coinsOnAllNonWalls) {
            this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);

        if (subTasks.stream().allMatch(subTask -> subTask.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        this.task = this.task.updateSubTasks(subTasks);
    }

    private boolean checkAllDroppedOnShape(List<Position> coinDrops, Set<Position> shape) {
        if (coinDrops.size() != shape.size()) {
            return false;
        }
        Set<Position> shapeCopy = new HashSet<>(shape);
        for (Position pos : coinDrops) {
            shapeCopy.remove(pos);
        }
        return shapeCopy.isEmpty();
    }

    private boolean checkAllDroppedThreeInLine(List<Position> coinDrops, Set<Position> line) {
        if (coinDrops.size() != (line.size() * 3)) {
            return false;
        }
        Set<Position> lineCopy = new HashSet<>(line);
        Set<Position> posCountA = new HashSet<>();
        Set<Position> posCountB = new HashSet<>();
        for (Position pos : coinDrops) {
            if (posCountA.contains(pos)) {
                if (posCountB.contains(pos)) {
                    lineCopy.remove(pos);
                } else {
                    posCountB.add(pos);
                }
            } else {
                posCountA.add(pos);
            }
        }
        return lineCopy.isEmpty();
    }

    private boolean checkAllDroppedInLineOnEven(List<Position> coinDrops, Set<Position> line) {
        Set<Position> lineCopy = new HashSet<>(line);
        for (Position pos : line) {
            if (pos.getX() % 2 == 0) {
                lineCopy.remove(pos);
            }
        }
        if (coinDrops.size() != lineCopy.size()) {
            return false;
        }
        for (Position pos : coinDrops) {
            lineCopy.remove(pos);
        }
        return lineCopy.isEmpty();
    }


    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }



}
