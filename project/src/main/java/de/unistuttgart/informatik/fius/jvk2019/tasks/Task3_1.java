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
import java.util.concurrent.ThreadLocalRandom;


/**
 * The task for exercise 1 (b) on worksheet 3
 * 
 * @author paulesn
 */
public abstract class Task3_1 extends TaskWithHelperFunctions {
    
    @Override
    public void prepare(Simulation arg0) {
        super.prepare(sim);
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public boolean verify() {
        /*
         * this counter should be 2023 at the end. 
         * 2023 is the amount of correct queries I do. 
         * Every incorrect query reduces the counter by one to prevent a false positive 
         */
        int counter = 0;
        
        //----i-------------------------------------------------------------------
        if (exI(12345)) {
            counter++;
        }
        loop: for (int i = 0; i != 1000; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            //check random value
            if (!(randomNum == 12345)) {
                //its a value that should be false
                if (exI(randomNum)) {
                    counter--;
                    break loop;
                }
            }
        }
        //----ii------------------------------------------------------------------
        
        //check edge case
        if (exII(15)) {
            if (exII(14)) {
                counter++;
            }
        }
        //check negative
        for (int i = 0; i != 10; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, 15);
            //its a value that should be false
            if (exII(randomNum)) {
                counter++;
            }
        }
        //check positive
        for (int i = 0; i != 1000; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(16, Integer.MAX_VALUE);
            //its a value that should be false
            if (exII(randomNum)) {
                counter--;
            }
        }
        //----iii-----------------------------------------------------------------
        if (exIII(22)) {
            counter--;
        }
        for (int i = 0; i != 1000; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            //check random value
            if (!(randomNum == 22)) {
                //its a value that should be true
                if (exIII(randomNum)) {
                    counter++;
                }
            }
        }
        //----iv------------------------------------------------------------------
        
        //check given range
        for (int i = 1; i <= 9; ++i) {
            if (exIV(i)) {
                counter++;
            }
        }
        //check edgecase
        if (exIV(0)) {
            counter--;
        }
        //check edgecase
        if (exIV(10)) {
            counter--;
        }
        
        //check random wrong numbers
        for (int i = 0; i != 1000; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            //check random value
            if (!(randomNum > 0 && randomNum < 10)) {
                //its a value that should be false
                if (exIII(randomNum)) {
                    counter--;
                }
            }
        }
        //----v-------------------------------------------------------------------
        
        //check given range
        for (int i = 1; i <= 9; ++i) {
            if (exIV(i)) {
                counter--;
            }
        }
        //check edgecase
        if (exIV(0)) {
            counter++;
        }
        //check edgecase
        if (exIV(10)) {
            counter++;
        }
        
        //check random right numbers
        for (int i = 0; i != 1000; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            //check random value
            if (!(randomNum > 0 && randomNum < 10)) {
                //its a value that should be false
                if (exIII(randomNum)) {
                    counter++;
                }
            }
        }
        
        //----vi------------------------------------------------------------------
        for (int i = 0; i != 1000; ++i) {
            //generate random value
            int randomNum = ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (!(exVI(randomNum) == (randomNum % 2 == 0))) {
                return false;
            }
        }
        
        // number check because the last exercise can't be checked that way
        Boolean test = false;
        if (counter == 2023) {
            test = true;
        }
        return test;
    }
    
    @Override
    public abstract void solve();
    
    /**
     * The solution to exercise 1 (i) on worksheet 3
     * 
     * @param value
     * @return true if the value was in the given parameters
     */
    public abstract Boolean exI(int value);
    
    /**
     * The solution to exercise 1 (ii) on worksheet 3
     * 
     * @param value
     * @return true if the value was in the given parameters
     */
    public abstract Boolean exII(int value);
    
    /**
     * The solution to exercise 1 (iii) on worksheet 3
     * 
     * @param value
     * @return true if the value was in the given parameters
     */
    public abstract Boolean exIII(int value);
    
    /**
     * The solution to exercise 1 (iv) on worksheet 3
     * 
     * @param value
     * @return true if the value was in the given parameters
     */
    public abstract Boolean exIV(int value);
    
    /**
     * The solution to exercise 1 (v) on worksheet 3
     * 
     * @param value
     * @return true if the value was in the given parameters
     */
    public abstract Boolean exV(int value);
    
    /**
     * The solution to exercise 1 (vi) on worksheet 3
     * 
     * @param value
     * @return true if the value was in the given parameters
     */
    public abstract Boolean exVI(int value);
    
}
