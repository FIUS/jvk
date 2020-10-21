package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Comparator;
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


public class Sheet3Task1Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Walk 10 steps (While)", "Walk 10 steps with a while loop.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Walk 10 steps (For)", "Walk exactly 10 steps with a for loop.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Walk X steps", "Walk 3, 7, 14, or 22 Steps with a for loop.");
    private BasicTaskInformation taskE = new BasicTaskInformation("e) Collect X coins", "Collect 2, 5, 16 or 20 coins with a for loop.");
    private BasicTaskInformation taskF = new BasicTaskInformation(
            "f) Nested Loops", "Pick up all the coins on the first field fith the second Neo. Walk 5 steps and drop as many coins as the number of steps you have walked so far."
    );
    private BasicTaskInformation taskG = new BasicTaskInformation("g) Nested Loops 2", "Walk 10 steps and pick up a maximum of 5 coins per field");
    private BasicTaskInformation taskH = new BasicTaskInformation("h) Nested Loops 3", "Walk and pick up every coin on the fields you walk over.");
    
    private ActionLog  actionLog;
    private SimulationClock clock;
    
    public Sheet3Task1Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);
        this.task = new BasicTaskInformation("Sheet 3 Task 1", "Loops.", subTasks);
    }
    
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.clock = sim.getSimulationClock();
    }
    

    
    @Override
    public void verify() {

        Comparator<Integer> nat = Comparator.naturalOrder();
        List<Entity> neoSpawns = this.actionLog.getActionsOfType(EntitySpawnAction.class, true).stream()
            .filter((action) -> action.getEntity() instanceof Neo)
            .sorted((a, b) -> nat.compare(a.getPosition().getY(), b.getPosition().getY()))
            .map((action) -> action.getEntity())
            .collect(Collectors.toList());

        if (neoSpawns.size() != 4) {
            this.task = this.task.updateDescription("Found " + neoSpawns.size() + "Neos but expected 4! Cannot verify Task.");
            this.task = this.task.updateStatus(TaskVerificationStatus.FAILED);
            return;
        }

        Entity neoA = neoSpawns.get(0);
        Entity neoF = neoSpawns.get(1);
        Entity neoG = neoSpawns.get(2);
        Entity neoH = neoSpawns.get(3);


        Optional<EntityStepAction> lastStepA = this.actionLog.getActionsOfTypeOfEntity(neoA, EntityStepAction.class, true).stream()
            .sorted((a, b) -> nat.compare(b.to().getX(), a.to().getX()))
            .findFirst();

        if (lastStepA.isPresent()) {
            Position stepTo = lastStepA.get().to();
            if (stepTo.getX() == 10) {
                this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }

            // check that neo could have moved another step forward
            Boolean noNextStep = (lastStepA.get().getTickNumber() + 5) < this.clock.getLastTickNumber();
            if (stepTo.getX() == 10 && noNextStep) {
                this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }

            if (noNextStep && (stepTo.getX() == 3 || stepTo.getX() == 7 || stepTo.getX() == 14 || stepTo.getX() == 22)) {
                this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL); // assume done
                this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL); // assume done
                this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            } else {
                if (stepTo.getX() > 10) {
                    this.taskB = this.taskB.updateStatus(TaskVerificationStatus.FAILED);
                    this.taskC = this.taskC.updateStatus(TaskVerificationStatus.FAILED);
                }
            }
        }

        int collectedCoinsA = this.actionLog.getActionsOfTypeOfEntity(neoA, EntityCollectAction.class, true).size();
        if (collectedCoinsA == 2 || collectedCoinsA == 5 || collectedCoinsA == 16 || collectedCoinsA == 20) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if (collectedCoinsA > 20) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.FAILED);
        }


        List<EntityDropAction> droppedCoinsF = this.actionLog.getActionsOfTypeOfEntity(neoF, EntityDropAction.class, true);
        if (checkNeoF(droppedCoinsF)) {
            this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        Optional<EntityStepAction> lastStepG = this.actionLog.getActionsOfTypeOfEntity(neoG, EntityStepAction.class, true).stream()
            .sorted((a, b) -> nat.compare(b.to().getX(), a.to().getX()))
            .findFirst();

        List<EntityCollectAction> collectedCoinsG = this.actionLog.getActionsOfTypeOfEntity(neoG, EntityCollectAction.class, true);
        if (checkNeoG(collectedCoinsG) && lastStepG.isPresent()) {
            if ((lastStepG.get().getTickNumber() + 5) < this.clock.getLastTickNumber()) {
                this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }

        Optional<EntityStepAction> lastStepH = this.actionLog.getActionsOfTypeOfEntity(neoH, EntityStepAction.class, true).stream()
            .sorted((a, b) -> nat.compare(b.to().getX(), a.to().getX()))
            .findFirst();

        long coinsNeoH = this.actionLog.getActionsOfType(EntitySpawnAction.class, true).stream()
            .filter((action) -> action.getEntity() instanceof Coin)
            .filter((action) -> {
                Position pos = action.getPosition();
                if (pos.getY() != 6) return false;
                return pos.getX() > 0 && pos.getX() < 11;
            })
            .map((action) -> action.getEntity())
            .distinct()
            .count();
        
        long coinsCollectedNeoH = this.actionLog.getActionsOfTypeOfEntity(neoH, EntityCollectAction.class, true).stream()
            .map((action) -> action.getCollectedEntity())
            .distinct()
            .count();
        
        if (coinsNeoH == coinsCollectedNeoH && lastStepH.isPresent()) {
            if ((lastStepH.get().getTickNumber() + 5) < this.clock.getLastTickNumber()) {
                this.taskH = this.taskH.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);

        if (subTasks.stream().allMatch(subTask -> subTask.getTaskStatus().equals(TaskVerificationStatus.SUCCESSFUL))) {
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        this.task = this.task.updateSubTasks(subTasks);
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
