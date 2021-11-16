// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Outtake extends SubsystemBase {

  private final WPI_TalonSRX mLeftMotor;
  private final WPI_TalonSRX mRightMotor;

  private final Servo mServo;

  ShuffleboardTab mMotorReadouts;

  public Outtake() {

    mLeftMotor = new WPI_TalonSRX(Constants.kOuttakeLeft);
    mRightMotor = new WPI_TalonSRX(Constants.kOuttakeRight);

    mLeftMotor.setNeutralMode(NeutralMode.Coast);
    mRightMotor.setNeutralMode(NeutralMode.Coast);

    mServo = new Servo(Constants.kGatePWM);
    mServo.setAngle(167);


  }

  public void run(double pSpeed) {

    mLeftMotor.set(-pSpeed);
    mRightMotor.set(-pSpeed);

  }

  public void toggleGate(){
    if(mServo.getAngle() == 167){ //Gate Closed
      mServo.setAngle(115);
    }
    else{
      mServo.setAngle(167);
    }
  }
}
