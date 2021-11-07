// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.PDP;

//TODO: Correct Amperage threshold / Make sure up is up / Tune min/max

public class LiftToHeight extends CommandBase {

  private final Lift mLift;
  private final PDP mPDP;
  private final double mSpeed;
  private double mSetpoint;

  public LiftToHeight(Lift pLift, PDP pPDP, double pSpeed, double pSetPoint) {

    mSpeed = pSpeed;
    mSetpoint = pSetPoint;
    mLift = pLift;
    mPDP = pPDP;

    addRequirements(mLift);
  }

  @Override
  public void execute() {

    double mMax = 30;
    double mMin = 0;

    if (mSetpoint > mMax) {
      mSetpoint = mMax;
    }
    if (mSetpoint < mMin) {
      mSetpoint = mMin;
    }

    if (mLift.getEncoderPosition() < mSetpoint) {
      mLift.run(mSpeed);
    } else {
      mLift.run(-mSpeed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    mLift.run(0);
  }

  @Override
  public boolean isFinished() {
    return ((mPDP.getCurrent(0) > 120) || (mLift.getEncoderPosition() < Math.abs(mSetpoint) + 0.2)
        || (mLift.getEncoderPosition() > Math.abs(mSetpoint) - 0.2));
  }
}
