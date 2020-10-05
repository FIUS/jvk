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


public class Sheet3Task2Verifier implements TaskVerifier {
    
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
    
    private ActionLog  actionLog;
    private Simulation sim;
    
    public Sheet3Task2Verifier() {
        
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
        this.task = new BasicTaskInformation("Sheet 3 Task 2", "If-Conditions.", subTasks);
        this.sim = sim;
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        
        this.actionLog = sim.getActionLog();
        //System.out.println("test2");
        preparePlayingField(sim);
    }
    
    private void preparePlayingField(Simulation sim) {
        
        PlayfieldModifier pm = new PlayfieldModifier(sim.getPlayfield());
        
    }
    
    @Override
    public void verify() {
        
    }
    
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
