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
        this.collect(this.getCurrentlyCollectableEntities(Coin.class, true).get(0));
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
     * checks if neo is currently standing on a field that also contains a phone booth
     *
     * @return true if neo stands on a field with a phone booth
     */
    @InspectionAttribute
    public Boolean isOnPhoneBooth() {
        if (this.getPlayfield().getEntitiesOfTypeAt(this.getPosition(), PhoneBooth.class, true).size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Turns Neo counter clockwise. Operation is to be implmented in MyNeo in Task 2.1.a)
     */
    @SuppressWarnings("static-method")
    public void turnCounterClockwise() {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }

    /**
     * Turns Neo around. Operation is to be implmented in MyNeo in Task 2.1.b).
     */
    @SuppressWarnings("static-method")
    public void turnAround() {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }

    /**
     * Gets the Balance of the current Neo. * Operation is to be implmented in MyNeo in Task 2.1.c) .
     *
     * @return the balance
     */
    @SuppressWarnings("static-method")
    public int getBalance() {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
    }

    /**
     * Adds an amount of coins to the wallet of Neo. Operation is to be implmented in MyNeo in Task 2.1.d) .
     *
     * @param amountOfCoins
     *     The amount to gain
     */
    @SuppressWarnings("static-method")
    public void gainCoins(int amountOfCoins) {
        throw new UnsupportedOperationException("This Method is to be implemented in the MyNeo class.");
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
