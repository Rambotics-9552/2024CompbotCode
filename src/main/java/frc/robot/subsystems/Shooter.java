package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    private final CANSparkFlex Motor1 = new CANSparkFlex(Constants.Shooter.Motor1Port, MotorType.kBrushless);
    private final CANSparkFlex Motor2 = new CANSparkFlex(Constants.Shooter.Motor2Port, MotorType.kBrushless);

    private final RelativeEncoder Motor1Encoder = Motor1.getEncoder();
    private final RelativeEncoder Motor2Encoder = Motor2.getEncoder();

    MotorControllerGroup shootMotors = new MotorControllerGroup(Motor1, Motor2);

    public final DigitalInput beamBrake = new DigitalInput(Constants.Shooter.beamBrakePort);

    public void Shoooter(){
        Motor2.setInverted(true);
    }

    public void runFlyForSpeaker(){
        shootMotors.set(Constants.Shooter.shootSpeaker);
    }

    public void runFlyForAmp(){
        shootMotors.set(Constants.Shooter.shootAmp);
    }


    public boolean FlyIsGoodForSpeaker(){
        return ((Motor1Encoder.getVelocity()+Motor2Encoder.getVelocity())/2<Constants.Shooter.minimumSpeed)&&(Math.abs(Motor1Encoder.getVelocity()-Motor2Encoder.getVelocity())>Constants.Shooter.minimumSpeed/10);
    }

    public boolean getBeamBreak(){
        return beamBrake.get();
    }

    public void intakeBack(){
        shootMotors.set(Constants.Shooter.intakeSpeed);
    }
}
