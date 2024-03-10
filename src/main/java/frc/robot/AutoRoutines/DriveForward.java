// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.AutoRoutines;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;
import frc.robot.commands.*;


public class DriveForward extends SequentialCommandGroup {

  /**
   just a basic auto to use as a base
   *
   * @param drive     The drive subsystem object used by the robot
   * @param right      The control input for the right sight of the drive
   * @param driveSubsystem The driveSubsystem subsystem to drive
   */
  public DriveForward(DriveBase drive, Intake intake, Shooter shooter) {
    super(
      new shootShooter(shooter, intake),
      new ParallelRaceGroup(
        new DriveStraight(drive, 3.6576),
        new intake(intake)),
      new DriveStraight(drive, -3.6576),
      new shootShooter(shooter, intake)
    );
  }
}