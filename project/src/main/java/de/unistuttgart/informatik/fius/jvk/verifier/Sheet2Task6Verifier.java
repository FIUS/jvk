package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.TaskVerifier;
import de.unistuttgart.informatik.fius.icge.simulation.actions.*;
import de.unistuttgart.informatik.fius.icge.ui.TaskInformation;
import de.unistuttgart.informatik.fius.icge.ui.TaskVerificationStatus;
import de.unistuttgart.informatik.fius.jvk.provided.BasicTaskInformation;
import de.unistuttgart.informatik.fius.jvk.provided.entity.Coin;


public class Sheet2Task6Verifier implements TaskVerifier {

    private BasicTaskInformation task;
    private Simulation sim;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Move Clockwise Along the Bushes", "Move clockwise along the bushes.");
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Move Counter-Clockwise Along the Bushes", "Move counterclockwise along the bushes.");



    private ActionLog actionLog;

    public Sheet2Task6Verifier() {
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        this.task = new BasicTaskInformation("Sheet 2 Task 6", "Truning clockwise and counterclockwise.", subTasks);
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {
        if(testTaskB()) {
            this.taskB = this.taskB.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        if(testTaskC()) {
            this.taskC = this.taskC.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }

        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskA);
        subTasks.add(this.taskB);
        subTasks.add(this.taskC);
        this.task = this.task.updateSubTasks(subTasks);
    }
    
    private boolean testTaskB(){
        List<EntityMoveAction> moveActions = this.actionLog.getActionsOfType(EntityMoveAction.class, true);
        List<Position> movePositions = moveActions.stream()
            .sorted((a, b) -> a.getTickNumber() < b.getTickNumber()? -1 : 1).map(a -> a.to()).collect(Collectors.toList());

        Iterator<Position> movePos = movePositions.iterator();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 8; j++){
                if(!movePos.hasNext())
                    return false;
                Position nextPos = movePos.next();
                if(i%2 == 0 && nextPos.getX() != i/2*8+(1-i)*(j+1))
                    return false;
                if(i%2 == 1 && nextPos.getY() != (i-1)/2*8+(-i+2)*(j+1))
                    return false;
            }
        }
        return true;
    }

    private boolean testTaskC(){
        List<EntityMoveAction> moveActions = this.actionLog.getActionsOfType(EntityMoveAction.class, true);
        List<Position> movePositions = moveActions.stream()
            .sorted((a, b) -> a.getTickNumber() < b.getTickNumber()? -1 : 1).map(a -> a.to()).collect(Collectors.toList());

        Iterator<Position> movePos = movePositions.iterator();
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 8; j++){
                if(!movePos.hasNext())
                    return false;
                Position nextPos = movePos.next();
                if(i%2 == 0 && nextPos.getY() != i/2*8+(1-i)*(j+1))
                    return false;
                if(i%2 == 1 && nextPos.getX() != (i-1)/2*8+(-i+2)*(j+1))
                    return false;
            }
        }
        return true;
    }


    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
}
