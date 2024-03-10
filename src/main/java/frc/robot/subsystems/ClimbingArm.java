package frc.robot.subsystems;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingArm extends SubsystemBase {
    private final CANSparkMax Motor1 = new CANSparkMax(Constants.Climber.ClimberMotor, MotorType.kBrushless);

    //private final RelativeEncoder Motor1Encoder = Motor1.getEncoder();

    public void ArmUp() {
        Motor1.set(Constants.Climber.MotorUp);
    }

    public void ArmDown() {
        Motor1.set(Constants.Climber.MotorDown);
    }


}
