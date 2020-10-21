package de.unistuttgart.informatik.fius.jvk.verifier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Iterator;
import java.util.stream.Collectors; 

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
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


public class Sheet3Task2Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskB = new BasicTaskInformation("b) find the first coin", "Walk until you find the first coin.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) walk straight", "Walk straight until you are on a field with one or more coins.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) left or right", "Turn right on a field with exactly one coin, but don't forget to collect the coin.");
    private BasicTaskInformation taskE = new BasicTaskInformation(
            "d) the other direction", "When on a field with more than one coin turn left, and collect one of the coins before stepping of the field."
    );
    private BasicTaskInformation taskF = new BasicTaskInformation("f) don't walk into walls", "Before you walk into a wall you should turn around.");
    private BasicTaskInformation taskG = new BasicTaskInformation("g) enough money", "Walk until you have collected 20 coins or step on a field with exactly 9 coins");
    
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
        Optional<Entity> maybeNeo = this.actionLog.getActionsOfType(EntitySpawnAction.class, true).stream()
            .filter((action) -> action.getEntity() instanceof Neo)
            .map((action) -> action.getEntity())
            .findFirst();

            
        List<Position> coinSpawns = this.actionLog.getActionsOfType(EntitySpawnAction.class, true).stream()
            .filter((action) -> action.getEntity() instanceof Coin)
            .filter((action) -> action.getTickNumber() < 1)
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

            
        Set<Position> walls = this.actionLog.getActionsOfType(EntitySpawnAction.class, true).stream()
            .filter((action) -> action.getEntity() instanceof Wall)
            .filter((action) -> action.getTickNumber() < 1)
            .map((action) -> action.getPosition())
            .collect(Collectors.toSet());

        Set<Position> anyCoinField = new HashSet<>(coinSpawns);

        Set<Position> turnRightFields = new HashSet<>();
        Set<Position> turnLeftFields = new HashSet<>();
        Set<Position> stopFields = new HashSet<>();

        int currentCoinCount = 0;
        Position lastPos = null;
        for (int i = 0; i<coinSpawns.size(); i++) {
            Position currentPos = coinSpawns.get(i);
            if (lastPos == null) {
                lastPos = currentPos;
                currentCoinCount++;
                continue;
            } else if (lastPos.equals(currentPos)) {
                currentCoinCount++;
                continue;
            }
            // position changed (new field)
            if (currentCoinCount == 9) {
                stopFields.add(lastPos);
            } else if (currentCoinCount == 1) {
                turnRightFields.add(lastPos);
            } else if (currentCoinCount > 1) {
                turnLeftFields.add(lastPos);
            }
            // cleanup
            currentCoinCount = 1;
            lastPos = currentPos;
        }
        if (currentCoinCount > 0) {
            if (currentCoinCount == 9) {
                stopFields.add(lastPos);
            } else if (currentCoinCount == 1) {
                turnRightFields.add(lastPos);
            } else if (currentCoinCount > 1) {
                turnLeftFields.add(lastPos);
            }
        }

        if (maybeNeo.isEmpty()) {
            this.task = this.task.updateDescription("Could not find Neo. Cannot verify this Task.");
            this.task = this.task.updateStatus(TaskVerificationStatus.FAILED);
        }

        Entity neo = maybeNeo.get();

        boolean reachedCoinField = this.actionLog.getActionsOfTypeOfEntity(neo, EntityStepAction.class, true).stream()
            .anyMatch(step -> stopFields.contains(step.to()) || turnLeftFields.contains(step.to()) || turnRightFields.contains(step.to()));
        
        if (reachedCoinField) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        List<EntityStepAction> neoSteps = this.actionLog.getActionsOfTypeOfEntity(neo, EntityStepAction.class, true);

        boolean correctLeftTurn = false;
        boolean correctRightTurn = false;
        boolean wrongLeftTurn = false;
        boolean wrongRightTurn = false;

        for (int i = 0; i < neoSteps.size() - 1; i++) {
            if (correctLeftTurn || wrongLeftTurn) {
                break;
            }

            Position to = neoSteps.get(i).to();
            if (turnLeftFields.contains(to)) {
                Position from = neoSteps.get(i).from();
                Position next = neoSteps.get(i+1).to();
                // check turn left
                if (from.adjacentPosition(Direction.EAST).equals(to)) {
                    if(to.adjacentPosition(Direction.NORTH).equals(next)) {
                        correctLeftTurn = true;
                    } else {
                        wrongLeftTurn = true;
                    }
                }
                if (from.adjacentPosition(Direction.SOUTH).equals(to)) {
                    if(to.adjacentPosition(Direction.EAST).equals(next)) {
                        correctLeftTurn = true;
                    } else {
                        wrongLeftTurn = true;
                    }
                }
                if (from.adjacentPosition(Direction.WEST).equals(to)) {
                    if(to.adjacentPosition(Direction.SOUTH).equals(next)) {
                        correctLeftTurn = true;
                    } else {
                        wrongLeftTurn = true;
                    }
                }
                if (from.adjacentPosition(Direction.NORTH).equals(to)) {
                    if(to.adjacentPosition(Direction.WEST).equals(next)) {
                        correctLeftTurn = true;
                    } else {
                        wrongLeftTurn = true;
                    }
                }
            }
        }

        for (int i = 0; i < neoSteps.size() - 1; i++) {
            if (correctRightTurn || wrongRightTurn) {
                break;
            }

            Position to = neoSteps.get(i).to();
            if (turnRightFields.contains(to)) {
                Position from = neoSteps.get(i).from();
                Position next = neoSteps.get(i+1).to();
                // check turn left
                if (from.adjacentPosition(Direction.EAST).equals(to)) {
                    if(to.adjacentPosition(Direction.SOUTH).equals(next)) {
                        correctRightTurn = true;
                    } else {
                        wrongRightTurn = true;
                    }
                }
                if (from.adjacentPosition(Direction.SOUTH).equals(to)) {
                    if(to.adjacentPosition(Direction.WEST).equals(next)) {
                        correctRightTurn = true;
                    } else {
                        wrongRightTurn = true;
                    }
                }
                if (from.adjacentPosition(Direction.WEST).equals(to)) {
                    if(to.adjacentPosition(Direction.NORTH).equals(next)) {
                        correctRightTurn = true;
                    } else {
                        wrongRightTurn = true;
                    }
                }
                if (from.adjacentPosition(Direction.NORTH).equals(to)) {
                    if(to.adjacentPosition(Direction.EAST).equals(next)) {
                        correctRightTurn = true;
                    } else {
                        wrongRightTurn = true;
                    }
                }
            }
        }

        
        //d)
        if (wrongRightTurn) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.FAILED);
        } else if (correctRightTurn) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        //e)
        if (wrongLeftTurn) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.FAILED);
        } else if (correctLeftTurn) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        boolean correctWallBounce = false;
        boolean wrongWallBounce = false;

        for (int i = 0; i < neoSteps.size() - 1; i++) {
            Position from = neoSteps.get(i).from();
            Position to = neoSteps.get(i).to();
            Position next = neoSteps.get(i+1).to();

            int deltaX = to.getX() - from.getX();
            if (deltaX != 0) {
                // moved horizontal
                if (walls.contains(new Position(to.getX() + deltaX, to.getY()))) {
                    if (from.equals(next)) {
                        correctWallBounce = true;
                    } else {
                        if (! anyCoinField.contains(to)) {
                            wrongWallBounce = true;
                        }
                    }
                }
            }

            int deltaY = to.getY() - from.getY();
            if (deltaY != 0) {
                // moved vertical
                if (walls.contains(new Position(to.getX(), to.getY() + deltaY))) {
                    if (from.equals(next)) {
                        correctWallBounce = true;
                    } else {
                        if (! anyCoinField.contains(to)) {
                            wrongWallBounce = true;
                        }
                    }
                }
            }
        }
        
        //f)
        if (wrongWallBounce) {
            this.taskF = this.taskF.updateStatus(TaskVerificationStatus.FAILED);
        } else if (correctWallBounce) {
            this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        

        long coinsInWallet = this.actionLog.getActionsOfTypeOfEntity(neo, EntityCollectAction.class, true).stream()
            .filter((action) -> action.getCollectedEntity() instanceof Coin)
            .map((action) -> action.getCollectedEntity())
            .distinct()
            .count();

        boolean reachedStopField = this.actionLog.getActionsOfTypeOfEntity(neo, EntityStepAction.class, true).stream()
            .filter(step -> stopFields.contains(step.to()))
            .count() == 1;
        boolean stoppedOnStopField = reachedStopField && this.actionLog.getActionsOfTypeOfEntity(neo, EntityStepAction.class, true).stream()
            .filter(step -> stopFields.contains(step.from()))
            .count() == 0;

        //g)
        if (coinsInWallet == 20 || reachedStopField) {
            this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
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
