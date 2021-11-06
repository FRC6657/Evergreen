// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  private WPI_TalonSRX mFrontLeft;
  private WPI_TalonSRX mFrontRight;
  private WPI_VictorSPX mBackLeft;
  private WPI_VictorSPX mBackRight;

  private SpeedControllerGroup mLeftMotors;
  private SpeedControllerGroup mRightMotors;

  public Drivetrain() {

    mFrontLeft = new WPI_TalonSRX(Constants.kFrontLeft);
    mFrontRight = new WPI_TalonSRX(Constants.kFrontRight);
    mBackLeft = new WPI_VictorSPX(Constants.kBackLeft);
    mBackRight = new WPI_VictorSPX(Constants.kBackRight);

    mLeftMotors = new SpeedControllerGroup(mFrontLeft, mBackLeft);
    mRightMotors = new SpeedControllerGroup(mFrontRight, mBackRight);

    Shuffleboard.getTab("Motors").add("Left Drivetrain Motors", mLeftMotors).withSize(2, 1).withPosition(0, 2);
    Shuffleboard.getTab("Motors").add("Right Drivetrain Motors", mRightMotors).withSize(2, 1).withPosition(2, 2);

  }

  public void rawDrive(double pLeftSpeed, double pRightSpeed) {
    mLeftMotors.set(pLeftSpeed);
    mRightMotors.set(pRightSpeed);
  }

  //TODO: Verify Drive Equations
  public void comboDrive(double pXSpeed, double pZRotation) {
    mLeftMotors.set(pXSpeed + pZRotation);
    mRightMotors.set(-pXSpeed + pZRotation);
  }
}
