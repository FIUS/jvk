/*
 * This source file is part of the FIUS JVK 2020 project.
 * For more information see github.com/FIUS/JVK
 *
 * Copyright (c) 2020 the FIUS JVK 2020 project authors.
 *
*/

package de.unistuttgart.informatik.fius.jvk.provided.entity;

import de.unistuttgart.informatik.fius.icge.simulation.inspection.InspectionAttribute;
import de.unistuttgart.informatik.fius.icge.simulation.inspection.InspectionMethod;
import de.unistuttgart.informatik.fius.jvk.Texture;
import de.unistuttgart.informatik.fius.jvk.provided.exceptions.NoNutException;
import de.unistuttgart.informatik.fius.jvk.provided.exceptions.TotoroIsHungryException;

import java.util.List;


/**
 * The Totoro entity
 *
 * @author Sara Galle
 */
public class Totoro extends Creature {
    
    /*
     *   TODO: Implement the operation "turnCounterClockwise" here
     */
    
    /*
     *   TODO: Paste your operation of sheet 3 task 4d here 
     */
    
    @Override
    protected String getTextureHandle() {
        return Texture.TOTORO.getHandle(this.getLookingDirection());
    }
    
    /**
     * collects a nut from the actual field
     *
     * @throws NoNutException
     *     when there is no nut
     */
    @InspectionMethod()
    public void collectNut() {
        if (this.canCollectNut()) {
            Nut nut = this.getCurrentlyCollectableEntities(Nut.class, true).get(0);
            this.collect(nut);
        } else {
            throw new NoNutException();
        }
        
    }
    
    /**
     * drops a nut from Totoro's inventory to the actual field
     *
     * @throws TotoroIsHungryException
     *     when Totoro has no nuts
     */
    @InspectionMethod()
    public void dropNut() {
        if (!this.canDropNut()) throw new TotoroIsHungryException();
        this.drop(this.getCurrentlyDroppableEntities(Nut.class, true).get(0));
    }
    
    /**
     *
     * @return whether Totoro can drop a nut
     */
    @InspectionAttribute
    public boolean canDropNut() {
        return this.getCurrentlyDroppableEntities(Nut.class, true).size() > 0;
    }
    
    /**
     *
     * @return whether there is a nut to collect
     */
    @InspectionAttribute
    public boolean canCollectNut() {
        return this.getCurrentlyCollectableEntities(Nut.class, true).size() > 0;
    }
    
    /**
     * 
     * @return a List of all Nuts on Totoros field
     */
    public List<Nut> getCurrentlyCollectableCoins() {
        return this.getCurrentlyCollectableEntities(Nut.class, true);
    }
    
    /**
     * Add the amount of nuts to the inventory
     *
     * @param nuts
     *     the amount of nuts to set
     */
    protected void setNuts(int nuts) {
        if (nuts < 0) throw new IllegalArgumentException("Cannot set negative nut count!");
        int existing = this.getNutsInPocket();
        if (existing < nuts) {
            for (int i = existing; i < nuts; i++) {
                this.getInventory().add(new Nut());
            }
        }
        if (existing > nuts) {
            for (int i = existing; i > nuts; i--) {
                this.getInventory().remove(this.getInventory().get(Nut.class, true).get(0));
            }
        }
    }
    
    /**
     * Helper Method that sets a fixed amount of nuts for easier verification. Used in Verification of Task 2.1
     *
     * @param amountOfNuts
     *     the new amount of nuts
     */
    public void setNutsInPocket(int amountOfNuts) {
        this.setNuts(amountOfNuts);//calling real method for good measure.
    }
    
    /**
     * @return the number of nuts in totoros wallet
     */
    @InspectionAttribute
    public int getNutsInPocket() {
        return this.getInventory().get(Nut.class, true).size();
    }
}
