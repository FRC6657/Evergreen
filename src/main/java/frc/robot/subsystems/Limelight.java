// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import oi.limelightvision.limelight.frc.LimeLight;
import oi.limelightvision.limelight.frc.ControlMode.CamMode;
import oi.limelightvision.limelight.frc.ControlMode.LedMode;

public class Limelight extends SubsystemBase {

  private LimeLight mLimelight;

  private SuppliedValueWidget<Boolean> mVisionEnabled = Shuffleboard.getTab("Vision")
      .addBoolean("Vision Enabled", () -> (mLimelight.getCamMode().getValue() == 0)).withPosition(0, 0);
  private SuppliedValueWidget<Boolean> mLightEnabled = Shuffleboard.getTab("Vision")
      .addBoolean("Light Enabled", () -> (mLimelight.getLEDMode().getValue() == 3)).withPosition(1, 0);

  public Limelight() {
    mLimelight = new LimeLight();
  }

  public void toggleVision() {
    if (mLimelight.getCamMode() == CamMode.kvision) {
      mLimelight.setCamMode(CamMode.kdriver);
    } else {
      mLimelight.setCamMode(CamMode.kvision);
    }
  }

  public void toggleLED() {
    if (mLimelight.getLEDMode() == LedMode.kforceOn) {
      mLimelight.setLEDMode(LedMode.kforceOff);
    } else {
      mLimelight.setLEDMode(LedMode.kforceOn);
    }
  }

  public boolean isTargetFound() {
    return mLimelight.getIsTargetFound();
  }

  public boolean isLimelightConnected() {
    return mLimelight.isConnected();
  }

  public double getTargetX() {
    return mLimelight.getdegRotationToTarget();
  }

  public double getTargetY() {
    return mLimelight.getdegVerticalToTarget();
  }

  public double getTargetSize() {
    return mLimelight.getTargetArea();
  }

  @Override
  public void periodic() {
  }
}
