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

import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Position;

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationClock;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;

/**
 * @author Jannik Graef
 */
public class ExtraSheetTask3Verifier implements TaskVerifier{
    
    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) fibbonacci", "fibbonacci.");
    private BasicTaskInformation task;
    private Playfield playfield;
    
    public ExtraSheetTask3Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        this.task = new BasicTaskInformation("Sheet 3 Task 1", "Loops.", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.playfield=sim.getPlayfield();
        
    }

    @Override
    public void verify() {
        boolean fib = this.playfield.getEntitiesAt(new Position(0,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count() == 1;
        fib = fib && this.playfield.getEntitiesAt(new Position(1,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==1;
        fib = fib && this.playfield.getEntitiesAt(new Position(2,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==2;
        fib = fib && this.playfield.getEntitiesAt(new Position(3,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==3;
        fib = fib && this.playfield.getEntitiesAt(new Position(4,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==5;
        fib = fib && this.playfield.getEntitiesAt(new Position(5,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==8;
        fib = fib && this.playfield.getEntitiesAt(new Position(6,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==13;
        fib = fib && this.playfield.getEntitiesAt(new Position(7,0)).stream().filter(entitie -> entitie.getClass().equals(Coin.class)).count()==21;
        if(fib) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
    }

    @Override
    public TaskInformation getTaskInformation() {
                 return this.task;
    }
    
}
