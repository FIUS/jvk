/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk2019.provided.entity;

import java.util.function.Supplier;


/**
 * // TODO
 */
public class StandardPhoneBoothProgram extends PhoneBoothProgram {
    
    private final Supplier<Boolean> goalReachedCondition;
    
    /**
     * Create a new Standard PhoneBoothProgram.
     * <p>
     * This program will check the requirementsChecker of the phoneBooth every simulation tick until
     * {@code goalReachedCondition} returns true;
     * 
     * @param goalReachedCondition
     *     the condition for this program to finish
     */
    public StandardPhoneBoothProgram(Supplier<Boolean> goalReachedCondition) {
        super();
        if (goalReachedCondition == null) throw new IllegalArgumentException("Goal reached  condition cannot be null!");
        this.goalReachedCondition = goalReachedCondition;
    }
    
    @Override
    public void run(PhoneBooth booth) {
        if (booth.getRequirementsChecker() == null) {
            booth.setRequirementsAsFulfilled();
            return;
        }
        booth.setRequirementsNotFulfilled();
        while (!this.goalReachedCondition.get()) {
            boolean requirementsMet = booth.getRequirementsChecker().get();
            if (requirementsMet) {
                booth.setRequirementsAsFulfilled();
            } else {
                booth.setRequirementsNotFulfilled();
            }
            booth.sleep(1);
        }
    }
}
