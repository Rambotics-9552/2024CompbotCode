package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    private final CANSparkMax intakeMotor1 = new CANSparkMax(Constants.intake.intakeMotor1Port, MotorType.kBrushed);


    private final MotorControllerGroup intakeMotors = new MotorControllerGroup(intakeMotor1);

    private DigitalInput beamBreak;

    public Intake(DigitalInput beamBreak){
        this.beamBreak = beamBreak;
    }

    public boolean getBeamBreak(){
        return beamBreak.get();
    }

    public void runIntake(){
        intakeMotors.set(Constants.intake.intakeSpeed);
    }

    public void index(){
        intakeMotors.set(Constants.intake.indexSpeed);
    }



    
}
