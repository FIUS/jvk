package de.unistuttgart.informatik.fius.jvk.verifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import de.unistuttgart.informatik.fius.jvk.provided.entity.Wall;
import de.unistuttgart.informatik.fius.jvk.tasks.SubTask;


public class Sheet2Task6Verifier implements TaskVerifier {

    private BasicTaskInformation task;
    private Simulation sim;

    private BasicTaskInformation taskA = new BasicTaskInformation("a) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskB = new BasicTaskInformation("b) Move Clockwise Along the Walls", "Move clockwise along the walls.");
    private BasicTaskInformation taskC = new BasicTaskInformation("b) Move Counter-Clockwise Along the Walls", "Move counterclockwise along the walls.");



    private ActionLog actionLog;

    public Sheet2Task6Verifier(SubTask subTask) {
        ArrayList<BasicTaskInformation> tasks = new ArrayList<>(Arrays.asList(new BasicTaskInformation[]{taskA, taskB, taskC}));
        int count = 0;
        for(SubTask subTasks : SubTask.values()){
            if(count - 1 > tasks.size())
                throw new IllegalArgumentException();
            if(subTasks == subTask)
                break;
            count++;
        }
        try {
            task = tasks.get(count);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void attachToSimulation(Simulation sim) {
        this.actionLog = sim.getActionLog();
        this.sim = sim;
    }

    @Override
    public void verify() {
        if(task.equals(taskB) && checkTaskB())
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);        
        if(task.equals(taskC) && checkTaskC())
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
    }
    
    private boolean checkTaskB(){
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

    private boolean checkTaskC(){
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
