// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
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

    mFrontLeft.setNeutralMode(NeutralMode.Brake);
    mFrontRight.setNeutralMode(NeutralMode.Brake);
    mBackLeft.setNeutralMode(NeutralMode.Brake);
    mBackRight.setNeutralMode(NeutralMode.Brake);

    mLeftMotors = new SpeedControllerGroup(mFrontLeft, mBackLeft);
    mRightMotors = new SpeedControllerGroup(mFrontRight, mBackRight);

  }

  public void rawDrive(double pLeftSpeed, double pRightSpeed) {
    mLeftMotors.set(pLeftSpeed);
    mRightMotors.set(pRightSpeed);
  }

  public void comboDrive(double pXSpeed, double pZRotation) {
    mLeftMotors.set(pXSpeed + pZRotation);
    mRightMotors.set(-pXSpeed + pZRotation);
  }
}
