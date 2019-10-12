/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

/**
 * A child class of Neo. This class is to be implemented by the students.
 *
 * TODO: 2.2.b),c) add your author Tag and another one
 */
public class MyNeo extends Neo {
    
    //TODO: 2.5.a) Add the missing attributes
    
    /**
     * Default empty constructor
     */
    public MyNeo() {
        
    }
    
    /**
     * Creates a new MyNeo that has a certaint amount of coins.
     * 
     * @param starterCoins
     *     coin count that this neo starts with
     */
    public MyNeo(int starterCoins) {
        setCoinsInWallet(starterCoins);
    }
    
    /**
     * Moves this Neo entity twice. Example implementation of a new operation.
     */
    public void moveTwice() {
        this.move();
        this.move();
    }
    
    //TODO: Task 2.2 a) add JavaDoc to the operation you just created
    //TODO: Task 2.1 a) create and implement turnCounterClockwise here:
    
    //TODO: Task 2.2 a) add JavaDoc to the operation you just created
    //TODO: Task 2.1 b) create and implement turnAround here:
    
    //TODO: Task 2.2 a) add JavaDoc to the operation you just created
    //TODO: Task 2.1 c) create and implement getBalance here:
    
    //TODO: Task 2.2 a) add JavaDoc to the operation you just created
    //TODO: Task 2.1 c) create and implement gainCoins here:
    
    //TODO: Task 2.5.d) Override the move command so it also counts the steps taken:
    
    //TODO: Task 2.5 b) add getter queries:
    
    //TODO: Task 2.5 c) add setter queries:
    
}
