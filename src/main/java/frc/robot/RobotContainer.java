// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.AutoRoutines.DriveForward;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.AutoRoutines.*;

//import frc.robot.commands.IntakeCommand;
//import frc.robot.commands.ReverseIntakeCommand;

import frc.robot.subsystems.DriveBase;

//import frc.robot.subsystems.BucketSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.XboxController.Axis;
import edu.wpi.first.wpilibj.XboxController.Button;
//import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.cscore.UsbCamera;
//import edu.wpi.first.networktables.NetworkTableEntry;
//import edu.wpi.first.networktables.NetworkTableInstance;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveBase m_driveSubsystem = new DriveBase();

  //private NetworkTableEntry cameraSelection;

  XboxController movementJoystick = new XboxController(Constants.MOVEMENT_JOYSTICK);
  XboxController manipulatorJoystick = new XboxController(Constants.MANIPULATOR_JOYSTICK);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    m_driveSubsystem.setDefaultCommand(
      new ArcadeDrive(
            m_driveSubsystem,
            () -> -((-movementJoystick.getRawAxis(Axis.kLeftTrigger.value) + movementJoystick.getRawAxis(Axis.kRightTrigger.value))),
            () -> (-movementJoystick.getRawAxis(Axis.kLeftX.value) * 0.75)
      ));


  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    final JoystickButton manipulator_x = new JoystickButton(manipulatorJoystick, Button.kX.value);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new DriveForward(m_driveSubsystem);
  }
}
