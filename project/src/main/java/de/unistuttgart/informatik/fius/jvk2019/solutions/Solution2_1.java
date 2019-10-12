/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.solutions;

import java.util.List;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.jvk2019.tasks.Task2_1;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;


/**
 * The solution for 2.1
 * Nothing needs to be done here by students.
 *  
 * @author Lion Wagner
 */
public class Solution2_1 extends Task2_1 {

    private MyNeo myNeo = new MyNeo();         
    
    @Override
    public void prepare(Simulation sim) {     
        super.prepare(sim);
        
        //adding myNeo to the playing field
        sim.getPlayfield().addEntity(new Position(0, 0), this.myNeo);
    }
  
    /**
     * An example that uses all the commands/operations that are to be implemented in Task 2.1. (except for 'gainCoins').
     * 
     * Feel free to move myNeo as you like.
     * Use CollectCoin whenever you want to try to pick up a coin. 
     */
    @Override
    public void solve() {
        this.myNeo.moveTwice();
        this.myNeo.turnAround();
        this.myNeo.moveIfPossible();
        this.myNeo.turnCounterClockwise();
        this.myNeo.move();
        CollectCoin();
    }    

    //Some helper operations for the task. Can be ignored and are not to be touched. -----------------------------------------------------------------
      
    private void CollectCoin()
    {   
        List<Coin> collectables = this.myNeo.getCurrentlyCollectableEntities(Coin.class, true);
        if(collectables.size()>0)
            this.myNeo.collect(collectables.get(0));
    }
    
}
