// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class ControlPanel extends SubsystemBase {

  private final WPI_VictorSPX mPivotMotor;
  private final WPI_TalonSRX mSpinMotor;

  private int mLoops = 0;
  private ShuffleboardTab mDriverstation = Shuffleboard.getTab("Driver Station");

  private NetworkTableEntry mPivotData =
    mDriverstation.add("Pivot", 0)
      .withPosition(2, 0)
      .withSize(2, 2)
      .withWidget(BuiltInWidgets.kDial)
      .withProperties(Map.of("showValue", false, "min", -100))
      .getEntry();
  private NetworkTableEntry mSpinData =
      mDriverstation.add("Spin", 0)
        .withPosition(4, 0)
        .withSize(2, 2)
        .withWidget(BuiltInWidgets.kDial)
        .withProperties(Map.of("showValue", false, "min", -100))
        .getEntry();

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
    mLoops += 1;
    if(mLoops == 5){
      mLoops = 0;
      mPivotData.setNumber(mPivotMotor.get() * 100);
      mSpinData.setNumber(mSpinMotor.get() * 100);
    }
  }
}
