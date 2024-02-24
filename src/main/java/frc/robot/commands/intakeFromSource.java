package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.Shooter;

public class intakeFromSource extends RunCommand {
    Shooter intake;
    public intakeFromSource(Shooter intake){
        super(()->{intake.intakeBack();}
        );
    }

}
