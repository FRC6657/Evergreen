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

@SuppressWarnings("unused")
public class Limelight extends SubsystemBase {

  private LimeLight mLimelight;

  

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

  public void setPipeline(int pPipeline) {
    mLimelight.setPipeline(pPipeline);
  }

  public boolean isTargetFound() {
    return mLimelight.getIsTargetFound();
  }

  public boolean isLimelightConnected() {
    return mLimelight.isConnected();
  }

  public boolean onTarget(){
    return (Math.abs(getTargetX()) < 0.2 && Math.abs(getTargetY()) < 0.2);
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

  public int getPipeline() {
    return mLimelight.getPipelineInt();
  }
}
