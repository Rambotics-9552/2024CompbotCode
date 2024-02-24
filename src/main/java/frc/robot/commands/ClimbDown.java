package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbingArm;

public class ClimbDown extends Command{
    public ClimbingArm cArm;

    public ClimbDown(ClimbingArm cArm){
        this.cArm=cArm;

        addRequirements(cArm);
    }

    @Override
    public void execute(){
        cArm.ArmDown();
    }

}
