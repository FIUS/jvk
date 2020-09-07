/*
 * This source file is part of the FIUS JVK 2020 project.
 * For more information see github.com/FIUS/JVK
 *
 * Copyright (c) 2020 the FIUS JVK 2020 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationBuilder;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.icge.ui.GameWindow;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;
import de.unistuttgart.informatik.fius.icge.ui.WindowBuilder;

import de.unistuttgart.informatik.fius.jvk.provided.Game;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.*;
import de.unistuttgart.informatik.fius.jvk.tasks.*;
import de.unistuttgart.informatik.fius.jvk.verifier.*;

/**
 * Main class of the project
 *
 * @author Tim Neumann
 */
public class Main {

    /**
     * The main entry point of the project
     *
     * @param args
     *     the command line args; not used
     */
    public static void main(String[] args) {
        Game myGame = new Game("Sheet2Task3", new Sheet2Task3(), null);
        myGame.run();
    }
}
