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
public class TankDrive extends RunCommand {

  public TankDrive(DriveBase drive, DoubleSupplier leftSpeed, DoubleSupplier rightSpeed) {
    super(
      ()->{
        drive.drive(
          MathUtil.applyDeadband(leftSpeed.getAsDouble(), 0.1),
          MathUtil.applyDeadband(rightSpeed.getAsDouble(), 0.1)
        );
      },
      drive
    );
  }
}