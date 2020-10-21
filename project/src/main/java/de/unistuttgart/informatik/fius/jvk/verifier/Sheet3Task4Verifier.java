package de.unistuttgart.informatik.fius.jvk.verifier;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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


/**
 *
 * @author Jannik Graef
 */
public class Sheet3Task4Verifier implements TaskVerifier {
    
    private BasicTaskInformation task;
    
    private BasicTaskInformation taskE = new BasicTaskInformation(
            "e)", "Walk the pattern on the exercise sheet.", TaskVerificationStatus.UNDECIDED
    );
    private ActionLog            actionLog;
    private Simulation           sim;
    
    public Sheet3Task4Verifier() {
        
        //System.gc();
        //System.out.println("test");
        List<BasicTaskInformation> subTasks = new ArrayList<>();
        subTasks.add(this.taskE);
        this.task = new BasicTaskInformation("Sheet 3 Task 4", "Operations.", subTasks);
    }
    
    @Override
    public void attachToSimulation(Simulation sim) {
        this.sim = sim;
        this.actionLog = sim.getActionLog();
        //System.out.println("test2");
    }
    
    @Override
    public void verify() {
        this.actionLog = this.sim.getActionLog();
        List<EntityTurnAction> turnActions = this.actionLog.getActionsOfType(EntityAction.class, true);
        List<Position> stepPositions = new ArrayList<>();
        turnActions.add(turnActions.get(0).from());
        turnActions.forEach(turnAction -> stepPositions.add(turnAction.from()));
        
        Position expectedPosition = new Position(0, 0);
        for (Integer runs = 0; runs < 7; runs++) {
            if (!(stepPositions.get(runs).equals(expectedPosition))) {
                this.taskE = this.taskE.updateStatus(TaskVerificationStatus.FAILED);
                break;
            }
            switch (runs % 4) {
                case 0:
                    expectedPosition = new Position(expectedPosition.getX() + runs + 1, expectedPosition.getY());
                    break;
                
                case 1:
                    expectedPosition = new Position(expectedPosition.getX(), expectedPosition.getY() + runs + 1);
                    break;
                
                case 2:
                    expectedPosition = new Position(expectedPosition.getX() - (runs + 1), expectedPosition.getY());
                    break;
                
                case 3:
                    expectedPosition = new Position(expectedPosition.getX(), expectedPosition.getY() - (runs + 1));
                    break;
            }
        }
        if (this.taskE.getTaskStatus() != TaskVerificationStatus.FAILED) {
            this.taskE = this.taskE.updateStatus(TaskVerificationStatus.SUCCESSFUL);
        }
        
    }
    
    @Override
    public TaskInformation getTaskInformation() {
        return this.task;
    }
    
}
