// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

@SuppressWarnings("unused")
public class Lift extends SubsystemBase {

  private final WPI_TalonSRX mMotor;

  private int mLoops = 0;
  private ShuffleboardTab mDriverstation = Shuffleboard.getTab("Driver Station");

  private NetworkTableEntry mData = mDriverstation
    .add("Lift", 0)
      .withPosition(4, 2)
      .withSize(2, 2)
      .withWidget(BuiltInWidgets.kDial)
      .withProperties(Map.of("showValue", false, "min", -100))
      .getEntry();

  public Lift() {
    mMotor = new WPI_TalonSRX(Constants.kLiftID);
    mMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void run(double pSpeed) {
    mMotor.set(pSpeed);
  }

  @Override
  public void periodic() {
    mLoops += 1;
    if(mLoops == 5){
      mLoops = 0;
      mData.setNumber(mMotor.get()*100);
    }
  }
}
