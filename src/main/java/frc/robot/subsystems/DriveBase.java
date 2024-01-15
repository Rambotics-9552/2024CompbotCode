// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveBase extends SubsystemBase {


  //creates the motors in code
  private final WPI_VictorSPX m_leftMoter = new WPI_VictorSPX(Constants.drive.LEFT_MOTOR);
  private final WPI_VictorSPX m_rightMoter = new WPI_VictorSPX(Constants.drive.RIGHT_MOTOR);
  private final WPI_VictorSPX m_leftBackMotor = new WPI_VictorSPX(Constants.drive.REAR_LEFT_MOTOR);
  private final WPI_VictorSPX m_rightBackMotor = new WPI_VictorSPX(Constants.drive.REAR_RIGHT_MOTOR);
  
  //puts the motors in groups for easier handling
  MotorControllerGroup m_LeftMotorGroup = new MotorControllerGroup(m_leftMoter, m_leftBackMotor);
  MotorControllerGroup m_RightMotorGroup = new MotorControllerGroup(m_rightMoter, m_rightBackMotor);

  private final DifferentialDrive m_RobotDrive = new DifferentialDrive(m_LeftMotorGroup, m_RightMotorGroup);



  public DriveBase() {


    m_leftMoter.setSafetyEnabled(false);
    m_rightBackMotor.setSafetyEnabled(false);
    m_rightMoter.setSafetyEnabled(false);
    m_rightBackMotor.setSafetyEnabled(false);

    // inverts two motors so the drivetrain can run
    m_leftMoter.setInverted(true);
    m_leftBackMotor.setInverted(true);



  }

  public void drive(final double ySpeed, final double rotateValue) {
    m_RobotDrive.arcadeDrive(ySpeed, -rotateValue);
  }
}
