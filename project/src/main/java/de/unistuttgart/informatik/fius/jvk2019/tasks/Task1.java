/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.tasks;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.RequirementChecks;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.StandardPhoneBoothProgram;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;


/**
 * An example task
 * 
 * @author Tim Neumann
 */
public abstract class Task1 extends TaskWithHelperFunctions {
    
    /**
     * The player character.
     */
    protected Neo player;
    
    /**
     * The phoneBooth used as the goal.
     */
    protected PhoneBooth goal;
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
        
        this.generateCage(3, 3);
        
        this.spawnEntity(new Wall(), 0, 1);
        this.spawnEntity(new Wall(), 0, 2);
        this.spawnEntity(new Wall(), 1, 1);
        this.spawnEntity(new Wall(), 1, 2);
        
        this.goal = new PhoneBooth();
        this.goal.setRequirementsChecker(() -> true);
        this.spawnEntity(this.goal, 2, 2);
        
        String goalChecker = this.registerProgram(
                "goalChecker", new StandardPhoneBoothProgram(RequirementChecks.testEntitiesOnSameField(() -> this.player, () -> this.goal))
        );
        this.bindProgramToEntity(goalChecker, this.goal);
    }
    
    @Override
    public boolean verify() {
        this.waitForEntitesToFinishProgram(this.goal);
        // all goal check are in phone booth program
        return true;
    }
    
}
