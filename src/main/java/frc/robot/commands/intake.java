package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class intake extends Command{
    private Intake intake;
    public intake(Intake intake){
        this.intake=intake;
    }

    @Override
    public void execute(){
        intake.runIntake();
    }

    @Override
    public boolean isFinished(){
        return intake.getBeamBreak();
    }
}
