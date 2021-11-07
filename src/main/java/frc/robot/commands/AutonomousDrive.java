// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutonomousDrive extends CommandBase {

  private Drivetrain mDrivetrain;
  private double mLeftSpeed;
  private double mRightSpeed;

  public AutonomousDrive(Drivetrain pDrivetrain, double pLeftSpeed, double pRightSpeed) {

    mDrivetrain = pDrivetrain;
    mLeftSpeed = pLeftSpeed;
    mRightSpeed = pRightSpeed;

    addRequirements(mDrivetrain);

  }

  @Override
  public void execute() {
    mDrivetrain.rawDrive(mLeftSpeed, mRightSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mDrivetrain.rawDrive(0, 0);
  }
}
