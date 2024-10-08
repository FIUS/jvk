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

import java.util.Random;


import de.unistuttgart.informatik.fius.icge.simulation.*;
import de.unistuttgart.informatik.fius.icge.simulation.programs.Program;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.*;
import de.unistuttgart.informatik.fius.icge.simulation.tools.*;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.factories.*;
import de.unistuttgart.informatik.fius.jvk.provided.programs.SafeWalkProgram;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;


/**
 * @author Clemens Lieb
 */
public class Sheet4Task4 implements Task {
    
    @Override
    public void run(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        Totoro totoro = new Totoro();
        pm.placeEntityAt(totoro, new Position(1, 3));

        Random rng = new Random();
        Line obstacle = new Line(new Position(3, -rng.nextInt(5)), new Position(3, rng.nextInt(10)));
        pm.placeEntityAtEachPosition(new BushFactory(), obstacle);
        // put your implementation below this comment

    }
    
}
