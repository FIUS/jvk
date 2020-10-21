/*
 * This source file is part of the FIUS JVK 2019 project.
 * For more information see github.com/FIUS/JVK-2019
 *
 * Copyright (c) 2019 the FIUS JVK 2019 project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.jvk.verifier;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.ActionLog;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;


/**
 * @author Maximilian Reichel
 */
public class Sheet3Task6Verifier implements TaskVerifier {
    
    private ActionLog  actionLog;
    private Simulation sim;
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskC = new BasicTaskInformation("c)", "Copy your operation of task 4 to the neo class.");
    private BasicTaskInformation taskD = new BasicTaskInformation(
            "d)", "Remove the parameter of type Neo from the operation in the neo class and replace all neo occurrences in this operation."
    );
    
    public Sheet3Task6Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        this.task = new BasicTaskInformation("Sheet 3 Task 6", "This.", subTasks);
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.sim = sim;
        this.actionLog = sim.getActionLog();
        
    }
    
    private static class VerificationNeo extends Neo {
        private Integer moveCount = 0;
        
        @Override
        public void move() {
            this.moveCount++;
        }
        
        public Integer getMoveCount() {
            return this.moveCount;
        }
    }
    
    private boolean verifyTaskC() throws ReflectiveOperationException {
        Method[] neoMethods = Neo.class.getMethods();
        for (Method method : neoMethods) {
            if (method.getParameterCount() == 2) {
                Class<?>[] params = method.getParameterTypes();
                if (params[0].isInstance(new Neo()) && params[1].isInstance(1)) {
                    // found method with matching signature. -> Check if this is move-n-times operation
                    VerificationNeo vNeo1 = new VerificationNeo();
                    method.invoke(vNeo1, vNeo1, 6);
                    VerificationNeo vNeo2 = new VerificationNeo();
                    method.invoke(vNeo2, vNeo2, 10);
                    if (vNeo1.getMoveCount() == 6 && vNeo2.getMoveCount() == 10) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean verifyTaskD() throws ReflectiveOperationException {
        Method[] neoMethods = Neo.class.getMethods();
        for (Method method : neoMethods) {
            if (method.getParameterCount() == 1) {
                Class<?>[] params = method.getParameterTypes();
                if (params[0].isInstance(1)) {
                    // found method with matching signature. -> Check if this is move-n-times operation
                    VerificationNeo vNeo1 = new VerificationNeo();
                    method.invoke(vNeo1, 6);
                    VerificationNeo vNeo2 = new VerificationNeo();
                    method.invoke(vNeo2, 10);
                    if (vNeo1.getMoveCount() == 6 && vNeo2.getMoveCount() == 10) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    @Override
    public void verify() {
        // search for user defined move-n-times operation
        try {
            if (verifyTaskC()) {
                this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        } catch (ReflectiveOperationException e) {
            // Nothing to do
        }
        
        try {
            if (verifyTaskD()) {
                this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        } catch (ReflectiveOperationException e) {
            // Nothing to do
        }
        
        // TODO Auto-generated method stub
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        this.task = this.task.updateSubTasks(subTasks);
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        // TODO Auto-generated method stub
        return this.task;
    }
    
}
