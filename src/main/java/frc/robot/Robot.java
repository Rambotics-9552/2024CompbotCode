/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.AutoRoutines.DriveForward;

import frc.robot.commands.*;
import frc.robot.subsystems.*;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  public DigitalInput beamBreak = new DigitalInput(Constants.Shooter.beamBrakePort);

  private final DriveBase m_driveSubsystem = new DriveBase();
  
  private final Shooter shooter = new Shooter(beamBreak);

  //private final revFly revFly = new revFly(shooter);


  private final Intake intake = new Intake(beamBreak);
  //private final intake intake = new intake(Intake);

  //private final shootShooter shoot = new shootShooter(shooter);

  private final ClimbingArm cArm = new ClimbingArm();

  SendableChooser<Integer> controlChooser = new SendableChooser<Integer>();
  //private NetworkTableEntry cameraSelection;

  private final CommandXboxController movementJoystick = new CommandXboxController(Constants.MOVEMENT_JOYSTICK);
  private final CommandXboxController manipulatorJoystick = new CommandXboxController(Constants.MANIPULATOR_JOYSTICK);

  /** The container for the robot. Contains subsystems, OI devices, and commands. 
   * @return */
    // Configure the button bindings
  public Robot(){

    controlChooser.setDefaultOption("arcade :)", 0);
    controlChooser.addOption("tank :(", 1);

    

    SmartDashboard.putData("control type", controlChooser);
    configureButtonBindings();
    
      

    
  }
  private void configureButtonBindings() {
    while (controlChooser==null){}


    if (controlChooser.getSelected()==0){
      m_driveSubsystem.setDefaultCommand(
        new ArcadeDrive(
              m_driveSubsystem,
              () -> ((-movementJoystick.getLeftTriggerAxis() + movementJoystick.getRightTriggerAxis())),
              () -> (movementJoystick.getLeftX() )
        ));

      manipulatorJoystick.a().whileTrue(new ClimbUp(cArm));
      manipulatorJoystick.b().whileTrue(new ClimbDown(cArm));
      manipulatorJoystick.leftTrigger().whileTrue(new revFly(shooter));
      manipulatorJoystick.rightTrigger().onTrue(new shootShooter(shooter, intake));
    }

    else if (controlChooser.getSelected()==1){
      m_driveSubsystem.setDefaultCommand(
        new TankDrive(
              m_driveSubsystem,
              () -> (movementJoystick.getLeftTriggerAxis()),
              () -> (movementJoystick.getRightTriggerAxis())
        ));

      manipulatorJoystick.a().whileTrue(new ClimbUp(cArm));
      manipulatorJoystick.b().whileTrue(new ClimbDown(cArm));
      manipulatorJoystick.leftTrigger().whileTrue(new revFly(shooter));
      manipulatorJoystick.rightTrigger().onTrue(new shootShooter(shooter, intake));
    }



    // final JoystickButton manipulator_x = new JoystickButton(manipulatorJoystick, Button.kX.value);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new DriveForward(m_driveSubsystem, intake, shooter);
  }
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
