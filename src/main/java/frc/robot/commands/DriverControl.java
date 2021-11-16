// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class DriverControl extends CommandBase {

  private Drivetrain mDrivetrain;
  private DoubleSupplier mDriveInput;
  private DoubleSupplier mTurnInput;
  private DoubleSupplier mDirection;
  private BooleanSupplier mTurbo;

  public DriverControl(Drivetrain pDrivetrain, DoubleSupplier pDriveInput, DoubleSupplier pTurnInput, DoubleSupplier pDirection, BooleanSupplier pTurbo) {

    mDrivetrain = pDrivetrain;
    mDriveInput = pDriveInput;
    mTurnInput = pTurnInput;
    mDirection = pDirection;
    mTurbo = pTurbo;

    addRequirements(mDrivetrain);

  }

  @Override
  public void execute() {
    if(mDirection.getAsDouble() > 0){
      if(mTurbo.getAsBoolean()){
        mDrivetrain.comboDrive(mDriveInput.getAsDouble() * 1, mTurnInput.getAsDouble() * 0.5);
      }
      else{
        mDrivetrain.comboDrive(mDriveInput.getAsDouble() * 0.6, mTurnInput.getAsDouble() * 0.5);
      }
    }
    else{
      if(mTurbo.getAsBoolean()){
        mDrivetrain.comboDrive(-mDriveInput.getAsDouble() * 1, -mTurnInput.getAsDouble() * 0.5);
      }
      else{
        mDrivetrain.comboDrive(-mDriveInput.getAsDouble() * 0.6, -mTurnInput.getAsDouble() * 0.5);
      } 
    }
  }

  @Override
  public void end(boolean interrupted) {
    mDrivetrain.rawDrive(0, 0);
  }
}
