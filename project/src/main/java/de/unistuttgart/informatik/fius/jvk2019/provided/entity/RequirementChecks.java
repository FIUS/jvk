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

import java.util.function.Predicate;
import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.entity.CollectableEntity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.GreedyEntity;
import de.unistuttgart.informatik.fius.icge.simulation.exception.EntityNotOnFieldException;


/**
 * Helper class holding requirement checks generators for the PhoneBooth.
 *
 * @author FabianBühler
 */
public class RequirementChecks {
    
    /**
     * Get a new supplier that returns true iff all clause suppliers are true.
     *
     * @param clauses
     *     the clause suppliers
     * @return the and supplier
     */
    final public static Supplier<Boolean> and(final Supplier<Boolean>... clauses) {
        if (clauses.length <= 0) throw new IllegalArgumentException("Cannot build AND of empty clause list!");
        return () -> {
            for (Supplier<Boolean> clause : clauses) {
                if (!clause.get()) {
                    return false;
                }
            }
            return true;
        };
    }
    
    /**
     * Get a new supplier that returns true iff at least one true clause supplier exists.
     *
     * @param clauses
     *     the clause suppliers
     * @return the or supplier
     */
    final public static Supplier<Boolean> or(final Supplier<Boolean>... clauses) {
        if (clauses.length <= 0) throw new IllegalArgumentException("Cannot build OR of empty clause list!");
        return () -> {
            for (Supplier<Boolean> clause : clauses) {
                if (clause.get()) {
                    return true;
                }
            }
            return false;
        };
    }
    
    /**
     * Get a new supplier that checks the number of Collectables of type {@code collectableType} in the {@code entity}
     * against the given number predicate.
     * <p>
     * The entity getter will be called for every evaluation of the supplier. If the entity getter returns null the
     * supplier returns false.
     * 
     * @param entityGetter
     *     the getter for the entity to check the inventory of
     * @param collectableType
     *     the collectable type to check the number of
     * @param numberPredicate
     *     the predicate to check the collectable count against
     * @return the created supplier
     */
    final public static Supplier<Boolean> testInventoryCount(
            final Supplier<GreedyEntity> entityGetter, Class<? extends CollectableEntity> collectableType,
            final Predicate<Integer> numberPredicate
    ) {
        if (entityGetter == null) throw new IllegalArgumentException("EntityGetter cannot be null!");
        if (collectableType == null) throw new IllegalArgumentException("Collectable Type cannot be null!");
        if (numberPredicate == null) throw new IllegalArgumentException("NumberPredicate cannot be null!");
        return () -> {
            final GreedyEntity entity = entityGetter.get();
            if (entity == null) return false;
            final int collectableCount = entity.getInventory().get(collectableType, true).size();
            return numberPredicate.test(collectableCount);
        };
    }
    
    /**
     * Get a new supplier that checks the number of Collectables of type {@link Coin} in the {@code entity} against the
     * given number predicate.
     * <p>
     * The entity getter will be called for every evaluation of the supplier. If the entity getter returns null the
     * supplier returns false.
     * 
     * @param entityGetter
     *     the getter for the entity to check the inventory of
     * @param numberPredicate
     *     the predicate to check the collectable count against
     * @return the created supplier
     */
    final public static Supplier<Boolean> testCoinCount(
            final Supplier<GreedyEntity> entityGetter, final Predicate<Integer> numberPredicate
    ) {
        return RequirementChecks.testInventoryCount(entityGetter, Coin.class, numberPredicate);
    }
    
    /**
     * Get a new supplier that checks if two entities are on the same position.
     * <p>
     * The entity getters will be called for every evaluation of the supplier. If any entity getter returns null the
     * supplier returns false.
     * <p>
     * If any of the entities is not on a playfield the supplier returns null.
     * 
     * @param a
     *     the getter for entity a
     * @param b
     *     the getter for entity b
     * @return the created supplier
     */
    final public static Supplier<Boolean> testEntitiesOnSameField(final Supplier<Entity> a, final Supplier<Entity> b) {
        if (a == null || b == null) throw new IllegalArgumentException("Entity getter cannot be null!");
        return () -> {
            try {
                final Entity entA = a.get();
                final Entity entB = b.get();
                if (entA == null || entB == null) return false;
                final Position posA = entA.getPosition();
                final Position posB = entB.getPosition();
                return posA.equals(posB);
            } catch (EntityNotOnFieldException e) {
                return false;
            }
        };
    }
    
    /**
     * Get a new supplier that checks if the entity is on the given position.
     * <p>
     * The entity getter will be called for every evaluation of the supplier. If the entity getter returns null the
     * supplier returns false.
     * 
     * @param entityGetter
     *     the getter for the entity to check the inventory of
     * @param x
     *     coordinate
     * @param y
     *     coordinate
     * @return the created supplier
     */
    final public static Supplier<Boolean> testEntityOnField(final Supplier<Entity> entityGetter, final int x, final int y) {
        if (entityGetter == null) throw new IllegalArgumentException("EntityGetter cannot be null!");
        final Position posToTest = new Position(x, y);
        return () -> {
            try {
                final Entity entity = entityGetter.get();
                if (entity == null) return false;
                final Position entityPos = entity.getPosition();
                return entityPos.equals(posToTest);
            } catch (EntityNotOnFieldException e) {
                return false;
            }
        };
    }
    
    /**
     * Get e new supplier that checks the number of Entites at {@code Position(x, y)} on the field against the given
     * numberPredicate.
     * 
     * @param field
     *     the playfield to check
     * @param entityType
     *     the entity type to look for (use {link Entity} to capture all entites)
     * @param numberPredicate
     *     the predicate to check the entity count against
     * @param x
     *     coordinate
     * @param y
     *     coordinate
     * @return the new supplier
     */
    final public static Supplier<Boolean> testEntytyCountOnField(
            final Playfield field, Class<? extends Entity> entityType, final Predicate<Integer> numberPredicate, final int x, final int y
    ) {
        if (field == null) throw new IllegalArgumentException("Playfield cannot be null!");
        if (entityType == null) throw new IllegalArgumentException("Entity Type cannot be null!");
        if (numberPredicate == null) throw new IllegalArgumentException("NumberPredicate cannot be null!");
        final Position posToTest = new Position(x, y);
        return () -> {
            final int entityCount = field.getEntitiesOfTypeAt(posToTest, entityType, true).size();
            return numberPredicate.test(entityCount);
        };
    }
    
    /**
     * Create a new greaterThan predicate.
     * 
     * @param compareValue
     *     the value to compare against
     * @return the predicate
     */
    public static Predicate<Integer> getGreaterThanPredicate(int compareValue) {
        return (number) -> number > compareValue;
    }
    
    /**
     * Create a new lesserThan predicate.
     * 
     * @param compareValue
     *     the value to compare against
     * @return the predicate
     */
    public static Predicate<Integer> getLesserThanPredicate(int compareValue) {
        return (number) -> number < compareValue;
    }
    
    /**
     * Create a new equalTo predicate.
     * 
     * @param compareValue
     *     the value to compare against
     * @return the predicate
     */
    public static Predicate<Integer> getEqualToPredicate(int compareValue) {
        return (number) -> number == compareValue;
    }
}
