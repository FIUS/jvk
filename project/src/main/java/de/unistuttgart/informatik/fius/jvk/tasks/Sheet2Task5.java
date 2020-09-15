package de.unistuttgart.informatik.fius.jvk.tasks;

import java.util.List;
import java.util.Random;

import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.tasks.Task;
import de.unistuttgart.informatik.fius.icge.simulation.tools.PlayfieldModifier;
import de.unistuttgart.informatik.fius.jvk.provided.entity.*;

public class Sheet2Task5 implements Task{
    private PlayfieldModifier pm;
    private SubTask subTask;

    public Sheet2Task5(SubTask subTask) {
        this.subTask = subTask;
    }

    private void subTaskC(Neo neo){
        /*
         TODO: Collect all coins on the playing field!
        */

        do {
            neo.move();
            List<Coin> coins = neo.getCurrentlyCollectableEntities(Coin.class, true);
            System.out.println(coins.size());
            while(coins.size() > 0){
                neo.collect(coins.get(0));
                coins = neo.getCurrentlyCollectableEntities(Coin.class, true);
                System.out.println("collect");
            }
            System.out.println("move");
        } while (neo.canMove());
    }

    private void subTaskD(Neo neo){
        /*
         TODO: Count how many coins were collected and place them in the center.
        */
    }

    private void subTaskE(Neo neo){
        /*
         TODO: Place on each reachable playing field at least one coin. 
        */
    }

    private void subTaskF(Neo neo){

    }

    private void subTaskG(Neo neo){
        
    }

    @Override
    public void run(Simulation sim) {
        this.pm = new PlayfieldModifier(sim.getPlayfield());
        Neo neo = new Neo();
        neo.setCoinsInWallet(100);
        this.pm.placeEntityAt(neo, new Position(0, 4));
        buildEnvironment();

        if(this.subTask == SubTask.C)
            subTaskC(neo);
        else if(this.subTask == SubTask.D)
            subTaskD(neo);
        else if(this.subTask == SubTask.E)
            subTaskE(neo);
        else if(this.subTask == SubTask.F)
            subTaskF(neo);
        else if(this.subTask == SubTask.G)
            subTaskG(neo);
    }

    private void buildEnvironment(){
        buildRectangle(-1, -1, 10, 10);
        
        Random r = new Random(System.nanoTime());
        for(int i = 1; i < 9; i++){
            int nextAmout = r.nextInt(1024);
            System.out.println("NEXT: " + nextAmout);
            nextAmout = ((10-(int)Math.floor(Math.log(nextAmout)/Math.log(2))) * 3);
            System.out.println("NEXT: " + nextAmout);
            for(int j = 0; j < nextAmout; j++){
                this.pm.placeEntityAt(new Coin(), new Position(i, 4));
            }
        }

        if(this.subTask == SubTask.G){
            this.pm.placeEntityAt(new Wall(), new Position(r.nextInt(9), 2));
            this.pm.placeEntityAt(new Wall(), new Position(r.nextInt(9), 2));
            this.pm.placeEntityAt(new Wall(), new Position(r.nextInt(9), 6));
            this.pm.placeEntityAt(new Wall(), new Position(r.nextInt(9), 6));
        }
    }

    private void buildRectangle(int x, int y, int width, int height){
        if(width <= 2 || height <= 2)
            throw new IllegalArgumentException();
        buildHorizontalWalls(y, x, x+width);
        buildHorizontalWalls(y+height, x, x+width);
        buildVerticalWalls(x, y+1, y+height-1);
        buildVerticalWalls(x+width, y+1, y+height-1);
    }

    private void buildHorizontalWalls(int y, int x1, int x2) throws IllegalArgumentException{
        if(x2 < x1)
            throw new IllegalArgumentException();
        for(int x = x1; x <= x2; x++)
            this.pm.placeEntityAt(new Wall(), new Position(x, y));
    }

    private void buildVerticalWalls(int x, int y1, int y2) throws IllegalArgumentException{
        if(y2 < y1)
            throw new IllegalArgumentException();
        for(int y = y1; y <= y2; y++)
            this.pm.placeEntityAt(new Wall(), new Position(x, y));
    }
}