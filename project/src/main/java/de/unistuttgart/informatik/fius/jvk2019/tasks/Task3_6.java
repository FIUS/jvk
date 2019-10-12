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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * TODO: Description
 * @author paulesn
 */
public abstract class Task3_6 extends TaskWithHelperFunctions {
    
    Boolean flag;
    @Override
    public void solve() {
        String[] a = {"Smith","Neo","Morpheus","Revolution","Trinity","Architect","Oracle"};
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(a));
        this.flag = searchNeo(list).equals("Neo");
    }
    
    /**
     * find neo
     * @param list
     * @return
     */
    public abstract String searchNeo(ArrayList<String> list);
    
    @Override
    public boolean verify() {
        return this.flag;
    }
    
}
