// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Blinkin extends SubsystemBase {

  private Spark mBlinkin;
  ShuffleboardTab mColorReadouts;

  public Blinkin() {

    mBlinkin = new Spark(Constants.kBlinkinPWM);
    mBlinkin.set(0);

    Shuffleboard.getTab("Color").add("Blinkin", mBlinkin).withSize(2, 1).withPosition(0, 0);

  }

  /**
   * <b>Important Values</b>
   * <p>
   * RED: 0.61
   * <p>
   * YELLOW: 0.69
   * <p>
   * BLUE: 0.87
   * <p>
   * GREEN: 0.77
   * <p>
   * 
   * @param pColor Desired Color Value | Range: (-1,1)
   *
   * @see https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
   * 
   */
  public void set(double pColor) {
    mBlinkin.set(pColor);
  }

  @Override
  public void periodic() {
  }
}
