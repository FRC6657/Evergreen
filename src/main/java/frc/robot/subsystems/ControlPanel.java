// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {

  private final WPI_VictorSPX mPivotMotor;
  private final WPI_TalonSRX mSpinMotor;

  ShuffleboardTab mMotorReadouts;

  public ControlPanel() {

    mPivotMotor = new WPI_VictorSPX(Constants.kControlPanelPivot);
    mSpinMotor = new WPI_TalonSRX(Constants.kControlPanelSpin);

    mSpinMotor.setNeutralMode(NeutralMode.Brake);
    mPivotMotor.setNeutralMode(NeutralMode.Brake);


  }

  public void pivot(double pSpeed) {
    mPivotMotor.set(pSpeed);
  }

  public void spin(double pSpeed) {
    mSpinMotor.set(pSpeed);
  }

  @Override
  public void periodic() {
    
  }
}
