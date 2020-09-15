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


public class Sheet2Task5Verifier implements TaskVerifier {

    private BasicTaskInformation task;
    private Simulation sim;

    private BasicTaskInformation taskB = new BasicTaskInformation("b) Select this task", "Select this task.", TaskVerificationStatus.SUCCESSFUL);
    private BasicTaskInformation taskC = new BasicTaskInformation("c) Collect all coins", "Collect all coins on the playing field.");
    private BasicTaskInformation taskD = new BasicTaskInformation("d) Reduce amount of coins", "Place exactly one coin on each playing field in the center line");
    private BasicTaskInformation taskE = new BasicTaskInformation("e) Create pattern of coins part 1", "Description: See exercise sheet");
    private BasicTaskInformation taskF = new BasicTaskInformation("e) Create pattern of coins part 2", "Description: See exercise sheet");
    private BasicTaskInformation taskG = new BasicTaskInformation("e) Create pattern of coins part 3", "Description: See exercise sheet");


    private ActionLog actionLog;

    public Sheet2Task5Verifier(SubTask subTask) {
        ArrayList<BasicTaskInformation> tasks = new ArrayList<>(Arrays.asList(new BasicTaskInformation[]{taskB, taskC, taskD, taskE, taskF, taskG}));
        int count = 0;
        for(SubTask subTasks : SubTask.values()){
            if(count - 1 > tasks.size())
                throw new IllegalArgumentException();
            if(subTasks == subTask)
                break;
            count++;
        }
        try {
            task = tasks.get(count-1);
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
        List<EntitySpawnAction> spawnActions = this.actionLog.getActionsOfType(EntitySpawnAction.class, true);

        List<Position> coinPositions = this.sim.getPlayfield().getAllEntitiesOfType(new Coin().getClass(),true).stream()
        .map(coin -> coin.getPosition())
        .sorted((a, b) -> {
            if (a.getY() < b.getY()) return -1;
            if (a.getY() == b.getY()) {
                if (a.getX() < b.getX()) return -1;
                if (a.getX() == b.getX()) return 0;
            }
            return 1;
        }).collect(Collectors.toList());

        for(Position pos : coinPositions)
            System.out.println(pos.getX() + " " + pos.getY());

        if(this.sim.getPlayfield().getAllEntitiesOfType(new Coin().getClass(),true).size() == 0 && taskC.equals(task))
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        else if(checkTaskD(coinPositions) && taskD.equals(task))
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        else if(checkTaskE(coinPositions) && taskE.equals(task))
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        else if(checkTaskF(coinPositions) && taskF.equals(task))
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        else if(checkTaskG(coinPositions) && taskG.equals(task))
            this.task = this.task.updateStatus(TaskVerificationStatus.SUCCESSFUL);
    }

    private boolean checkTaskD(List<Position> coinPositions){
        if(coinPositions.size() != 9)
            return false;
        int x = 0;
        for(Position coinPosition : coinPositions){
            if(coinPosition.getY() != 4 || coinPosition.getX() != x)
                return false;
            x++;
        }
        return true;
    }

    private boolean checkTaskE(List<Position> coinPositions){
        if(coinPositions.size() != 45)
            return false;

        int[] amount_vals = new int[]{2, 1, 2};
        int count = 0;
        for(int y = 3; y < 6; y++){
            for(int x = 0; x < 10; x++){
                for(int a = 0; a < amount_vals[y-3]; a++){
                    if(coinPositions.get(count).getX() != x || coinPositions.get(count).getY() != y)
                        return false;
                    count++;
                }
            }
        }

        return true;
    }

    private boolean checkTaskF(List<Position> coinPositions){
        if(coinPositions.size() != 101)
            return false;

        int index = 0;
        for(int y = 0; y < 9; y++){
            int offset = 0;
            for(int x = y%2; x < 9; x+=2){
                int val = -(y/2) + offset;
                offset++;
                val = Math.abs(val) + 1;
                for(int a = 0; a < val; a++){
                    if(coinPositions.get(index).getX() != x || coinPositions.get(index).getY() != y)
                        return false;
                    index++;
                }
            }
            System.out.println();
        }

        return true;
    }

    private boolean checkTaskG(List<Position> coinPositions){
        int[] amount_vals = new int[]{2, 1, 2};
        int count = 0;
        for(int y = 3; y < 6; y++){
            for(int x = 0; x < 10; x++){
                if(checkSurrounding(x, y)){
                    for(int a = 0; a < amount_vals[y-3]; a++){
                        if(coinPositions.get(count).getX() != x || coinPositions.get(count).getY() != y)
                            return false;
                        count++;
                    }
                }
            }
        }

        if(coinPositions.size() != count)
            return false;

        return true;
    }

    private boolean checkSurrounding(int x, int y){
        List<Position> wallPositions = this.sim.getPlayfield().getAllEntitiesOfType(Wall.class, true).stream()
        .map(wall -> wall.getPosition())
        .collect(Collectors.toList());

        if(wallPositions.contains(new Position(x, y+1)))
            return false;
        if(wallPositions.contains(new Position(x+1, y)))
            return false;
        if(wallPositions.contains(new Position(x, y-1)))
            return false;
        if(wallPositions.contains(new Position(x-1, y)))
            return false;

        return true;
    }

    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
}
