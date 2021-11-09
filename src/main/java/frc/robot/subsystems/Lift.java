// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;


@SuppressWarnings("unused")
public class Lift extends SubsystemBase {

  private final WPI_TalonSRX mMotor;
  ShuffleboardTab mMotorReadouts;

  public Lift() {

    mMotor = new WPI_TalonSRX(Constants.kLiftID);

    mMotor.setNeutralMode(NeutralMode.Brake);

    Shuffleboard.getTab("Motors").add("Lift", mMotor).withSize(2, 1).withPosition(0, 3);

  }

  public void run(double pSpeed) {
    mMotor.set(pSpeed);
  }
}
