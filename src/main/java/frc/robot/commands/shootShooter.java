package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;

public class shootShooter extends Command{
    public Shooter shooter;
    public Intake intake;
    public int count=0;

    public shootShooter(Shooter shooter, Intake intake){
        this.shooter=shooter;
        this.intake=intake;
    }

    @Override
    public void initialize(){
        count=0;
    }

    @Override
    public void execute(){
        if (shooter.FlyIsGoodForSpeaker()){
            intake.index();
        }
        shooter.runFlyForSpeaker();
    }
 
    @Override
    public boolean isFinished(){
        if (!shooter.getBeamBreak()){
            count++;
        }
        return count>50;
    }
}
