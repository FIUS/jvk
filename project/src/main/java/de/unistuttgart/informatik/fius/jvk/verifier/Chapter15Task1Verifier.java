/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.verifier;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntityDespawnAction;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntitySpawnAction;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.GreedyNeo;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * @author Sara Galle
 */
public class Chapter15Task1Verifier implements TaskVerifier {

    private ActionLog  actionLog;
    private Simulation sim;

    private BasicTaskInformation task;

    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskC = new BasicTaskInformation("c)", "Place a GreedyNeo object in the simulation");
    private BasicTaskInformation taskD = new BasicTaskInformation(
            "d)", "Place 5 coins on GreedyNeos position and collect them."
    );
    private BasicTaskInformation taskE = new BasicTaskInformation(
            "d)", "Place a Morpheus object in the simulation and try collecting a coin that does not exist."
    );
    private BasicTaskInformation taskF = new BasicTaskInformation(
            "d)", "Try letting Morpheus walk into a wall."
    );

    public Chapter15Task1Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        this.task = new BasicTaskInformation("Sheet 3 Task 6", "This.", subTasks);
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.sim = sim;
        this.actionLog = sim.getActionLog();
        
    }
    
    @Override
    public void verify() {
        // search for user defined move-n-times operation
        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);
        Optional<Entity> maybePlayer = spawnActions.stream()
                .map((action) -> action.getEntity())
                .filter((entity) -> (entity instanceof GreedyNeo))
                .findFirst();
        if (maybePlayer.isPresent()) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
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
        int nrOfCoinsSpawned = coinPositions.size();
        boolean allCollected = spawnActions.stream()
                .filter((action) -> (action.getEntity() instanceof Coin))
                .map((action) -> action.getEntity())
                .map((coin) -> this.actionLog.getActionsOfTypeOfEntity(coin, EntityDespawnAction.class, true))
                .allMatch((despawns) -> despawns.size() == 1);
        if (nrOfCoinsSpawned >= 5 && allCollected) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        Optional<Entity> maybePlayer2 = spawnActions.stream()
                .map((action) -> action.getEntity())
                .filter((entity) -> (entity instanceof GreedyNeo))
                .findFirst();
        if (maybePlayer2.isPresent()) {
            //assumes task is done
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        List<Position> wallPositions = spawnActions.stream()
                .filter((action) -> (action.getEntity() instanceof Wall))
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
            int nrOfWallsSpawned = wallPositions.size();
            if (nrOfWallsSpawned >= 1 && maybePlayer2.isPresent()) {
              //assumes task is done
                this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        // TODO Auto-generated method stub
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        this.task = this.task.updateSubTasks(subTasks);
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        // TODO Auto-generated method stub
        return this.task;
    }
    
}
