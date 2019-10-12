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

import java.util.Iterator;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Direction;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntityCollectAction;
import de.unistuttgart.informatik.fius.icge.simulation.actions.EntityDropAction;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;


/**
 * An example task
 * 
 * @author Tim-Julian Ehret
 */
public abstract class Task2_4 extends TaskWithHelperFunctions {
    
    @Override
    public void prepare(Simulation sim) {
        super.prepare(sim);
    }
    
    @Override
    public boolean verify() {
        List<MyNeo> neos = this.sim.getPlayfield().getAllEntitiesOfType(MyNeo.class, false);
        if (neos.size() != 4) {
            return false;
        }
        
        MyNeo richNeo = null;
        MyNeo poorNeo = null;
        
        for (Iterator<MyNeo> iterator = neos.iterator(); iterator.hasNext();) {
            MyNeo myNeo = iterator.next();
            if (myNeo.getLookingDirection() != Direction.EAST) {
                return false;
            }
            if (this.getCoinCount(myNeo) == 998) {
                richNeo = myNeo;
            }
            if (this.getCoinCount(myNeo) == 2) {
                poorNeo = myNeo;
            }
        }
        
        //check log for the transfer of coins between the neos...            
        List<EntityDropAction> drops = this.sim.getActionLog().getActionsOfType(EntityDropAction.class, false);
        List<EntityCollectAction> collects = this.sim.getActionLog().getActionsOfType(EntityCollectAction.class, false);
        if (drops.size() != 2 || collects.size() != 2) return false;
        
        //making final copies for stream api
        final MyNeo richNeoCopy = richNeo;
        final MyNeo poorNeoCopy = poorNeo;
        if (
            !drops.stream().allMatch(
                    (dropAction) -> dropAction.getEntity() == richNeoCopy && dropAction.getDroppedEntity().getClass() == Coin.class
            )
        ) return false;
        if (
            !collects.stream().allMatch(
                    collectAction -> collectAction.getEntity() == poorNeoCopy && collectAction.getCollectedEntity().getClass() == Coin.class
            )
        ) return false;
        
        return poorNeo != null && richNeo != null;
    }
    
}
