/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019;

import de.unistuttgart.informatik.fius.icge.simulation.SimulationHost;
import de.unistuttgart.informatik.fius.icge.simulation.SimulationHostFactory;
import de.unistuttgart.informatik.fius.icge.simulation.entity.EntityTypeRegistry;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.TaskRegistry;
import de.unistuttgart.informatik.fius.icge.ui.TextureRegistry;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_2;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_3;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution0_4;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution2_1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution2_4;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_1;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_2;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_3a;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_3b;
import de.unistuttgart.informatik.fius.jvk2019.solutions.Solution3_4;
import de.unistuttgart.informatik.fius.jvk2019.provided.Color;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Morpheus;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.MyNeo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.PhoneBooth;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk2019.provided.entity.Pill;
import de.unistuttgart.informatik.fius.jvk2019.solutions.*;


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
        SimulationHost host = SimulationHostFactory.createSimulationHost();
        prepareTextures(host.getTextureRegistry());
        prepareEntityTypes(host.getEntityTypeRegistry());
        prepareTasks(host.getTaskRegistry());
    }
    
    private static void prepareTextures(TextureRegistry tr) {
        // load textures
        for (Texture texture : Texture.values()) {
            texture.load(tr);
        }
    }
    
    private static void prepareEntityTypes(EntityTypeRegistry etr) {
        etr.registerEntityType("Wall", Texture.WALL.getHandle(), Wall.class);
        etr.registerEntityType("Coin", Texture.COIN.getHandle(), Coin.class);
        etr.registerEntityType("Red Pill", Texture.REDPILL.getHandle(), () -> new Pill(Color.RED));
        etr.registerEntityType("Blue Pill", Texture.BLUEPILL.getHandle(), () -> new Pill(Color.BLUE));
        etr.registerEntityType("Phone", Texture.PHONEBOOTH.getHandle(), PhoneBooth.class);
        //etr.registerEntityType("Neo", Texture.NEO.getHandle(), Neo.class);
        //etr.registerEntityType("My Neo", Texture.NEO.getHandle(), MyNeo.class);
        //etr.registerEntityType("Morpheus", Texture.MORPHEUS.getHandle(), Morpheus.class);
    }
    
    private static void prepareTasks(TaskRegistry tr) {
        tr.registerTask("Task0 a)", new Solution0_1());
        tr.registerTask("Task0 b)", new Solution0_2());
        tr.registerTask("Task0 c)", new Solution0_3());
        tr.registerTask("Task0 d)", new Solution0_4());
        tr.registerTask("Task1", new Solution1());
        tr.registerTask("Task2 1", new Solution2_1());
        tr.registerTask("Task2 2", new Solution2_2());
        tr.registerTask("Task2 4", new Solution2_4());
        tr.registerTask("Task3_1", new Solution3_1());
        tr.registerTask("Task3_2", new Solution3_2());
        tr.registerTask("Task3_3 (a)", new Solution3_3a());
        tr.registerTask("Task3_3 (b)", new Solution3_3b());
        tr.registerTask("Task3_4", new Solution3_4());
        tr.registerTask("Task3_5", new Solution3_5());
    }
}
