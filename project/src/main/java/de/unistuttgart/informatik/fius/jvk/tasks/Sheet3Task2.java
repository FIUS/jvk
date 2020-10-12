/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.tasks;

import java.util.Iterator;
import java.util.Random;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.CoinFactory;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;

/**
 * TODO: Description
 * @author Jannik
 */
public class Sheet3Task2 implements Task{
    @Override
    public void run(Simulation sim) {
        preparePlayingField(sim);
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        pm.placeEntityAt(neo, new Position(0, 0));
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void preparePlayingField(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        pm.placeEntityAt(new Coin(), new Position(4, 0));
        pm.placeMultipleEntitiesAt(() -> new Coin(), 3, new Position(4, 4));
        pm.placeEntityAt(new Coin(), new Position(12, 4));
        new Line(new Position(13, 3), new Position(13, 6)).forEach(p -> pm.placeEntityAt(new Wall(), new Position(p.getX(), p.getY())));
        new Line(new Position(-10, 7), new Position(13, 7)).forEach(p -> pm.placeEntityAt(new Wall(), new Position(p.getX(), p.getY())));
        new Line(new Position(14,3), new Position(30,3)).forEach(p -> pm.placeEntityAt(new Wall(), new Position(p.getX(), p.getY())));
        pm.placeEntityAt(new Coin(), new Position(12, 2));
        pm.placeMultipleEntitiesAt(() -> new Coin(), 7, new Position(new Random().nextInt(3)+13, 2));
    }
    
}
