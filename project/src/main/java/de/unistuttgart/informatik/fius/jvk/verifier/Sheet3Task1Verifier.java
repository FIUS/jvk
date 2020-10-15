package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Neo;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Line;
import de.unistuttgart.informatik.fius.jvk.provided.shapes.Rectangle;
import de.unistuttgart.informatik.fius.jvk.tasks.Sheet3Task1;


public class Sheet3Task1Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Walk 10 steps", "Walk 10 steps.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Walk 10 steps 2nd try", "Walk exactly 10 steps.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d)", "See excercise sheet.");
    private BasicTaskInformation taskE = new BasicTaskInformation("e)", "See excercise sheet.");
    private BasicTaskInformation taskF = new BasicTaskInformation(
            "f)", "Pick up all the coins on the first field. Walk 5 steps and drop as many coins as steps you have walked so far."
    );
    private BasicTaskInformation taskG = new BasicTaskInformation("g)", "Walk 10 steps and pick up a maximum of 5 coins per field");
    private BasicTaskInformation taskH = new BasicTaskInformation("h)", "Walk and pick up every coin on the fields you walk over.");
    
    private ActionLog  actionLog;
    private Simulation sim;
    
    public Sheet3Task1Verifier() {
        
        //System.gc();
        System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);
        this.task = new BasicTaskInformation("Sheet 3 Task 1", "Loops.", subTasks);
    }
    
    
    @Override
    public void attachToSimulation(Simulation sim) {
        
        this.actionLog = sim.getActionLog();
        System.out.println("test2");
    }
    

    
    @Override
    public void verify() {
        System.out.println("test3");
        
        List<EntityStepAction> stepActions = this.actionLog.getActionsOfType(EntityStepAction.class, true);
        List<Position> stepPositions = new ArrayList<>();
        stepActions.forEach(stepAction -> stepPositions.add(stepAction.to()));
        if (stepPositions.contains(new Position(10, 0)) && (!stepPositions.contains(new Position(11, 0)))) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        if ((getNumberOfCoinsAtPosition(30, 2) == 0) && (getNumberOfCoinsAtPosition(30, 14) == 1)) {
            this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        if (
            (getNumberOfCoinsAtPosition(30, 18) == 3) && (getNumberOfCoinsAtPosition(40, 18) == 5)
                    && (getNumberOfCoinsAtPosition(45, 18) == 1)
        ) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        boolean taskFFlag = true;
        for (int i = 1; i < 6; i++) {
            if (getNumberOfCoinsAtPosition(i, 2) != i) {
                taskFFlag = false;
            }
        }
        
        if (taskFFlag) {
            this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        

        if((getNumberOfCoinsAtPosition(1, 4)==0)
           &&(getNumberOfCoinsAtPosition(2, 4)==2)
           &&(getNumberOfCoinsAtPosition(3, 4)==0)
           &&(getNumberOfCoinsAtPosition(4, 4)==4)
           &&(getNumberOfCoinsAtPosition(5, 4)==1)
           &&(getNumberOfCoinsAtPosition(6, 4)==0)
           &&(getNumberOfCoinsAtPosition(7, 4)==0)
           &&(getNumberOfCoinsAtPosition(8, 4)==0)
           &&(getNumberOfCoinsAtPosition(9, 4)==3)
           &&(getNumberOfCoinsAtPosition(10, 4)==0)) {
            this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        };
        
        
        boolean taskHFlag = true;
        for (int i = 1; i < 11; i++) {
            if (getNumberOfCoinsAtPosition(i, 6) != 0) {
                taskHFlag = false;
            }
        }
        
        if (taskHFlag) {
            this.taskH = this.taskH.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);
        this.task = this.task.updateSubTasks(subTasks);
    }
    
   
    
    private int getNumberOfCoinsAtPosition(int x, int y) {
        return getNumberOfCoinsAtPosition(new Position(x, y));
    }
    
    private int getNumberOfCoinsAtPosition(Position pos) {
        return this.sim.getPlayfield().getEntitiesOfTypeAt(pos, Coin.class, true).size();
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        System.out.println("Test3");
        return this.task;
    }
    
}
