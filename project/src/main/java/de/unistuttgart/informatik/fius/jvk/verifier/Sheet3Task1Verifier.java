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
import de.unistuttgart.informatik.fius.jvk.tasks.Sheet3Task1;


public class Sheet3Task1Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskA = new BasicTaskInformation(
            "a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL
    );
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Walk 10 steps", "Walk exactly 10 steps.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Walk 10 steps 2nd try", "Walk exactly 10 steps.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d)", "See excercise sheet.");
    private BasicTaskInformation taskE = new BasicTaskInformation("e)", "See excercise sheet.");
    private BasicTaskInformation taskF = new BasicTaskInformation(
            "f)", "Pick up all the coins on the first field. Walk 5 steps and drop as many coins as steps you have walked so far."
    );
    private BasicTaskInformation taskG = new BasicTaskInformation("g)", "Walk 10 steps and pick up a maximum of 5 coins per field");
    private BasicTaskInformation taskH = new BasicTaskInformation("h)", "Walk and pick up every coin on the fields you walk over.");
    
    private ActionLog actionLog;
    
    public Sheet3Task1Verifier() {
        
        //System.gc();
        //System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        subTasks.add(this.taskD);
        subTasks.add(this.taskE);
        subTasks.add(this.taskF);
        subTasks.add(this.taskG);
        subTasks.add(this.taskH);
        this.task = new BasicTaskInformation("Sheet 1 Task 5", "Learn how to use the Playfield modifier.", subTasks);
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        //System.out.println("test2");
        preparePlayingField(sim);
    }
    
    private void preparePlayingField(Simulation sim) {
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        pm.placeEntityAt(new Coin(), new Position(30, 5));
        for (int i = 0; i < 100; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 61));
        }
        for (int i = 0; i < 9; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 76));
        }
        
        //g)
        for (int i = 0; i < 4; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 77));
        }
        for (int i = 0; i < 5; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 78));
        }
        for (int i = 0; i < 2; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 79));
        }
        for (int i = 0; i < 4; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 80));
        }
        for (int i = 0; i < 3; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 81));
        }
        for (int i = 0; i < 5; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 82));
        }
        for (int i = 0; i < 5; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 83));
        }
        for (int i = 0; i < 1; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 84));
        }
        for (int i = 0; i < 0; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 85));
        }
        for (int i = 0; i < 5; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 86));
        }
        
        //h)
        for (int i = 0; i < 7; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 87));
        }
        for (int i = 0; i < 5; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 88));
        }
        for (int i = 0; i < 6; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 89));
        }
        for (int i = 0; i < 12; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 90));
        }
        for (int i = 0; i < 4; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 91));
        }
        for (int i = 0; i < 0; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 92));
        }
        for (int i = 0; i < 10; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 93));
        }
        for (int i = 0; i < 1; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 94));
        }
        for (int i = 0; i < 8; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 95));
        }
        for (int i = 0; i < 4; i++) {
            pm.placeEntityAt(new Coin(), new Position(30, 96));
        }
    }
    
    @Override
    public void verify() {
        //System.out.println("test3");
        
        ArrayList<Action> actions = (ArrayList<Action>) this.actionLog.getAllActions();
        
        Comparator<Action> actionCompartor = new Comparator<Action>() {
            
            @Override
            public int compare(Action o1, Action o2) {
                // TODO Auto-generated method stub
                return Long.compare(o1.getTickNumber(), o2.getTickNumber());
            }
            
        };
        
        actions.sort(actionCompartor);
        
        List<Class> solutionClasses = new ArrayList<Class>();
        
        //spawning
        for (int i = 0; i < 202; i++) {
            solutionClasses.add(EntitySpawnAction.class);
        }
        
        //b) and c)
        for (int i = 0; i < 10; i++) {
            solutionClasses.add(EntityStepAction.class);
        }
        int cBorder = solutionClasses.size();
        
        //d)
        solutionClasses.add(EntityTurnAction.class);
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityStepAction.class);
        }
        solutionClasses.add(EntityTurnAction.class);
        solutionClasses.add(EntityTurnAction.class);
        solutionClasses.add(EntityTurnAction.class);
        for (int i = 0; i < 20; i++) {
            solutionClasses.add(EntityStepAction.class);
        }
        solutionClasses.add(EntityDespawnAction.class);
        solutionClasses.add(EntityCollectAction.class);
        solutionClasses.add(EntityTurnAction.class);
        for (int i = 0; i < 56; i++) {
            solutionClasses.add(EntityStepAction.class);
        }
        solutionClasses.add(EntitySpawnAction.class);
        solutionClasses.add(EntityDropAction.class);
        
        int dBorder = solutionClasses.size();
        //e)
        for (int i = 0; i < 72; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        for (int i = 0; i < 10; i++) {
            solutionClasses.add(EntityStepAction.class);
        }
        for (int i = 0; i < 72; i++) {
            solutionClasses.add(EntitySpawnAction.class);
            solutionClasses.add(EntityDropAction.class);
        }
        for (int i = 0; i < 42; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityStepAction.class);
        }
        solutionClasses.add(EntitySpawnAction.class);
        solutionClasses.add(EntityDropAction.class);
        int eBorder = solutionClasses.size();
        //f)
        for (int i = 0; i < 14; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        for (int i = 1; i < 6; i++) {
            solutionClasses.add(EntityStepAction.class);
            for (int k = 0; k < i; k++) {
                solutionClasses.add(EntitySpawnAction.class);
                solutionClasses.add(EntityDropAction.class);
            }
        }
        int fBorder = solutionClasses.size();
        
        //g)
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 4; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 2; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 4; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 3; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 1; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 0; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        int gBorder = solutionClasses.size();
        
        //h)
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 7; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 5; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 6; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 12; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 4; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 0; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 10; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 1; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 8; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        solutionClasses.add(EntityStepAction.class);
        for (int i = 0; i < 4; i++) {
            solutionClasses.add(EntityDespawnAction.class);
            solutionClasses.add(EntityCollectAction.class);
        }
        
        //solutionClasses.forEach(a -> System.out.println(a));
        // subtask b)
        
        List<Class> actionClasses = new ArrayList<Class>();
        actions.forEach(a -> actionClasses.add(a.getClass()));
        
        if (actionClasses.size() > cBorder + 1) {
            if (
                (actionClasses.subList(0, cBorder).equals(solutionClasses.subList(0, cBorder)))
                        && (actionClasses.get(cBorder + 1) == EntityTurnAction.class)
            ) {
                this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
                this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        } else if ((actionClasses.size() == cBorder)) {
            if (
                (actionClasses.subList(0, cBorder).equals(solutionClasses.subList(0, cBorder)))
            
            ) {
                this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
                this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        
        if (actionClasses.size() > dBorder) {
            if ((actionClasses.subList(0, dBorder).equals(solutionClasses.subList(0, dBorder)))) {
                this.taskD = this.taskD.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        
        if (actionClasses.size() > eBorder) {
            if ((actionClasses.subList(0, eBorder).equals(solutionClasses.subList(0, eBorder)))) {
                this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        
        if (actionClasses.size() > fBorder) {
            if ((actionClasses.subList(0, fBorder).equals(solutionClasses.subList(0, fBorder)))) {
                this.taskF = this.taskF.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        
        if (actionClasses.size() > gBorder) {
            if ((actionClasses.subList(0, gBorder).equals(solutionClasses.subList(0, gBorder)))) {
                this.taskG = this.taskG.updateStatus(TaskVerificationStatus.SUCCESSFUL);
            }
        }
        if (actionClasses.equals(solutionClasses)) {
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
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
