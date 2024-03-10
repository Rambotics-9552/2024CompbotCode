package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;
import java.lang.Math;


public class DriveStraight extends CommandBase {
    // Encoder leftEncoder = new Encoder(4,5); 
    // Encoder rightEncoder = new Encoder(5);


    private final DriveBase driveBase;
    private final PIDController pid = new PIDController(
        Constants.auto.kp,
        Constants.auto.ki,
        Constants.auto.kd
    );


    public DriveStraight(DriveBase driveSubsystem, double meters) {
      driveBase = driveSubsystem;

      addRequirements(driveBase);
      pid.setTolerance(Constants.auto.tolerence);
      pid.setSetpoint(meters);

        //50=12*10/(6*3.14)*X
    
    //   encoderValue = ((((3*12)//converts feet to inches
    //   /((Constants.auto.wheelRadius*3.14*2))//converts inches to wheel rotations
    //   *Constants.drive.gearRatio)// converts wheel rotations to motor rotations
    //   *Constants.auto.TicksPerRotation)//converts motor rotations to encoder ticks
    //   );

    

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        driveBase.resetEncoder();


    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {

        driveBase.drive(pid.calculate(driveBase.getEncoder()), 0);
    
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        driveBase.drive(0, 0);

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return pid.atSetpoint();
    }

      // Called repeatedly when this Command is scheduled to run

}