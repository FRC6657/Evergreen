// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Outtake extends SubsystemBase {

  private final WPI_TalonSRX mLeftMotor;
  private final WPI_TalonSRX mRightMotor;

  ShuffleboardTab mMotorReadouts;

  public Outtake() {

    mLeftMotor = new WPI_TalonSRX(Constants.kOuttakeLeft);
    mRightMotor = new WPI_TalonSRX(Constants.kOuttakeRight);
    
    Shuffleboard.getTab("Motors")
      .add("Outtake Left", mLeftMotor)
      .withSize(2, 1)
      .withPosition(0, 1)
    ;

    Shuffleboard.getTab("Motors")
      .add("Outtake Right", mRightMotor)
      .withSize(2, 1)
      .withPosition(2, 1)
    ;

  }

  public void run(double pSpeed){

    mLeftMotor.set(-pSpeed);
    mRightMotor.set(pSpeed);

  }

  @Override
  public void periodic() {
    
  }
}
