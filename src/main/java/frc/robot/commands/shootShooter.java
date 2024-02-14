package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Shooter;

public class shootShooter extends Command{
    public Shooter shooter;

    public shootShooter(Shooter shooter){
        this.shooter=shooter;
    }

    @Override
    public void initialize(){
        if (!shooter.FlyIsGoodForSpeaker()){
            CommandScheduler.getInstance().cancel(this);
        }
    }

    @Override
    public void execute(){
        shooter.runIndex();
        shooter.runFlyForSpeaker();
    }
 
    @Override
    public boolean isFinished(){
        return shooter.getBeamBreak();
    }
}
