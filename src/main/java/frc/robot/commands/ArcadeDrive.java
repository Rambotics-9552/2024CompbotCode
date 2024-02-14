/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveBase;

/**
 * Have the robot drive tank style.
 */
public class ArcadeDrive extends RunCommand {
  /**
   * Creates a new ArcadeDrive command.
   * @param driveSubsystem The driveSubsystem subsystem to drive
   * @param speed      The forward speed value for the robot
   * @param right      The turn speed value for the robot
   */
  public ArcadeDrive(DriveBase drive, DoubleSupplier speed, DoubleSupplier rotation) {
    super(
      ()->{
        drive.drive(
          MathUtil.applyDeadband(speed.getAsDouble(), 0.1)*Constants.drive.DRIVE_SPEED_MULTIPLYER,
          MathUtil.applyDeadband(rotation.getAsDouble(), 0.1)
        );
      },
      drive
    );
  }
}