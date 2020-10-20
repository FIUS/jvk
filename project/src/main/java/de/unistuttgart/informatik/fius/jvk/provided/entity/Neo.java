/*
 * This source file is part of the FIUS JVK 2020 project.
 * For more information see github.com/FIUS/JVK
 *
 * Copyright (c) 2020 the FIUS JVK 2020 project authors.
 *
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.provided.entity;

import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.inspection.InspectionAttribute;
import de.unistuttgart.informatik.fius.icge.simulation.inspection.InspectionMethod;
import de.unistuttgart.informatik.fius.jvk.Texture;
import de.unistuttgart.informatik.fius.jvk.provided.exceptions.NeoIsBrokeException;
import de.unistuttgart.informatik.fius.jvk.provided.exceptions.NoCoinException;


/**
 * The Neo entity
 *
 * @author Tim Neumann
 */
public class Neo extends Human {

    /*
     *   TODO: Implement the method "turnCounterClockwise" here
     */
    
    /*
     *   TODO: Paste your operation of sheet 3 task 4d here 
     */

    @Override
    protected String getTextureHandle() {
        return Texture.NEO.getHandle(this.getLookingDirection());
    }

    /**
     * collects a coin from the actual field
     *
     * @throws NoCoinException
     *     when there is no coin
     */
    @InspectionMethod()
    public void collectCoin() {
        if (!this.canCollectCoin()) throw new NoCoinException();
        Coin coin = this.getCurrentlyCollectableEntities(Coin.class, true).get(0);
        this.collect(coin);
    }

    /**
     * drops a coin from Neo's inventory to the actual field
     *
     * @throws NeoIsBrokeException
     *     when Neo is broken
     */
    @InspectionMethod()
    public void dropCoin() {
        if (!this.canDropCoin()) throw new NeoIsBrokeException();
        this.drop(this.getCurrentlyDroppableEntities(Coin.class, true).get(0));
    }

    /**
     *
     * @return whether Neo can drop a coin
     */
    @InspectionAttribute
    public boolean canDropCoin() {
        return this.getCurrentlyDroppableEntities(Coin.class, true).size() > 0;
    }

    /**
     *
     * @return whether there is a coin to collect
     */
    @InspectionAttribute
    public boolean canCollectCoin() {
        return this.getCurrentlyCollectableEntities(Coin.class, true).size() > 0;
    }
    
    /**
     * 
     * @return a List of all Coins on Neos field 
     */
    public List<Coin> getCurrentlyCollectableCoins(){
        return this.getCurrentlyCollectableEntities(Coin.class, true);
    }

    /**
     * Add the amount of coins to the inventory
     *
     * @param coins
     *     the amount of coins to set
     */
    protected void setCoins(int coins) {
        if (coins < 0) throw new IllegalArgumentException("Cannot set negative coin count!");
        int existing = this.getCoinsInWallet();
        if (existing < coins) {
            for (int i = existing; i < coins; i++) {
                this.getInventory().add(new Coin());
            }
        }
        if (existing > coins) {
            for (int i = existing; i > coins; i--) {
                this.getInventory().remove(this.getInventory().get(Coin.class, true).get(0));
            }
        }
    }

    /**
     * Helper Method that sets a fixed amount of coins for easier verification. Used in Verification of Task 2.1
     *
     * @param amountOfCoins
     *     the new amount of coins
     */
    public void setCoinsInWallet(int amountOfCoins) {
        this.setCoins(amountOfCoins);//calling real method for good measure.
    }

    /**
     * @return the number of coins in neos wallet
     */
    @InspectionAttribute
    public int getCoinsInWallet() {
        return this.getInventory().get(Coin.class, true).size();
    }
}
