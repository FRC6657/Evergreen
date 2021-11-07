// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriverControl extends CommandBase {

  private Drivetrain mDrivetrain;
  private DoubleSupplier mDriveInput;
  private DoubleSupplier mTurnInput;

  public DriverControl(Drivetrain pDrivetrain, DoubleSupplier pDriveInput, DoubleSupplier pTurnInput) {

    mDrivetrain = pDrivetrain;
    mDriveInput = pDriveInput;
    mTurnInput = pTurnInput;

    addRequirements(mDrivetrain);

  }

  @Override
  public void execute() {
    mDrivetrain.comboDrive(mDriveInput.getAsDouble(), mTurnInput.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
    mDrivetrain.rawDrive(0, 0);
  }
}