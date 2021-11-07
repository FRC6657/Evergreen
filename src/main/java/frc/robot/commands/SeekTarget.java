// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class SeekTarget extends CommandBase {

  private final Limelight mLimelight;
  private final Drivetrain mDrivetrain;

  public SeekTarget(Limelight pLimelight, Drivetrain pDrivetrain) {

    mLimelight = pLimelight;
    mDrivetrain = pDrivetrain;

    addRequirements(mLimelight, mDrivetrain);

  }

  @Override
  public void execute() {
    mDrivetrain.comboDrive(0, 0.4);
  }

  @Override
  public void end(boolean interrupted) {
    mDrivetrain.rawDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return (mLimelight.isTargetFound() || !mLimelight.isLimelightConnected());
  }
}
