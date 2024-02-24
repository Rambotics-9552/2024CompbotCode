package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbingArm;

public class ClimbUp extends Command{
    public ClimbingArm cArm;

    public ClimbUp(ClimbingArm cArm){
        this.cArm=cArm;

        addRequirements(cArm);
    }

    @Override
    public void execute(){
        cArm.ArmUp();
    }


}
