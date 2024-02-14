package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private final CANSparkMax intakeMotor1 = new CANSparkMax(Constants.intake.intakeMotor1Port, MotorType.kBrushless);
    private final CANSparkMax intakeMotor2 = new CANSparkMax(Constants.intake.intakeMotor2Port, MotorType.kBrushed);

    private final MotorControllerGroup intakeMotors = new MotorControllerGroup(intakeMotor1, intakeMotor2);

    private final DigitalInput beamBreak = new DigitalInput(Constants.intake.beamBrakePort);

    public Intake(){
        intakeMotor2.setInverted(true);
    }

    public boolean getBeamBreak(){
        return beamBreak.get();
    }

    public void runIntake(){
        intakeMotors.set(Constants.intake.intakeSpeed);
    }

    
}
