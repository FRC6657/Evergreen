// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private final WPI_TalonSRX mMotor;
  ShuffleboardTab mMotorReadouts;

  public Intake() {

    mMotor = new WPI_TalonSRX(Constants.kIntakeID);

    mMotor.setNeutralMode(NeutralMode.Coast);

    //Shuffleboard.getTab("Motors").add("Intake", mMotor).withSize(2, 1).withPosition(2, 4);

  }

  public void run(double pSpeed) {
    mMotor.set(pSpeed);
  }
}
