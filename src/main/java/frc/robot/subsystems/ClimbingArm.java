package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingArm extends SubsystemBase {
    private final CANSparkFlex Motor1 = new CANSparkFlex(Constants.Climber.ClimberMotor, MotorType.kBrushless);

    //private final RelativeEncoder Motor1Encoder = Motor1.getEncoder();

    public void ArmUp() {
        Motor1.set(Constants.Climber.MotorUp);
    }

    public void ArmDown() {
        Motor1.set(Constants.Climber.MotorDown);
    }


}
