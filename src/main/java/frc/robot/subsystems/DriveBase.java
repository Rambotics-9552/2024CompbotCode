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
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class DriveBase extends SubsystemBase {


  //creates the motors in code
  private final CANSparkMax m_leftMoter = new CANSparkMax(Constants.drive.LEFT_MOTOR, MotorType.kBrushless);
  private final CANSparkMax m_rightMoter = new CANSparkMax(Constants.drive.RIGHT_MOTOR, MotorType.kBrushless);
  private final CANSparkMax m_leftBackMotor = new CANSparkMax(Constants.drive.REAR_LEFT_MOTOR, MotorType.kBrushless);
  private final CANSparkMax m_rightBackMotor = new CANSparkMax(Constants.drive.REAR_RIGHT_MOTOR, MotorType.kBrushless);
  
  //puyts the motors in groups for easier handling
  MotorControllerGroup m_LeftMotorGroup = new MotorControllerGroup(m_leftMoter, m_leftBackMotor);
  MotorControllerGroup m_RightMotorGroup = new MotorControllerGroup(m_rightMoter, m_rightBackMotor);

  RelativeEncoder leftEncoder = m_leftMoter.getEncoder();
  RelativeEncoder rightEncoder = m_rightMoter.getEncoder();

  private final DifferentialDrive m_RobotDrive = new DifferentialDrive(m_LeftMotorGroup, m_RightMotorGroup);
  



  public DriveBase() {


    // inverts two motors so the drivetrain can run
    m_leftMoter.setInverted(true);
    m_leftBackMotor.setInverted(true);
    leftEncoder.setPositionConversionFactor(Constants.drive.conversionFactor);
    rightEncoder.setPositionConversionFactor(Constants.drive.conversionFactor);




  }

  public void drive(final double ySpeed, final double rotateValue) {
    m_RobotDrive.arcadeDrive(ySpeed, -rotateValue);
  }

  public double getEncoder(){
    return (leftEncoder.getPosition()+rightEncoder.getPosition())/2;
  }

  public void resetEncoder(){
    leftEncoder.setPosition(0);
    rightEncoder.setPosition(0);
  }

  public void arcadeDrive(double leftSpeed, double rightSpeed){
    m_RobotDrive.tankDrive(leftSpeed, rightSpeed);
  }
}
