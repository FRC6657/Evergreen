// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Limelight;

public class AutoTarget extends CommandBase {

  private final Limelight mLimelight;
  private final Drivetrain mDrivetrain;

  public AutoTarget(Limelight pLimelight, Drivetrain pDrivetrain) {

    mLimelight = pLimelight;
    mDrivetrain = pDrivetrain;

    addRequirements(mLimelight, mDrivetrain);

  }

  @Override
  public void execute() {

    if(mLimelight.getTargetY() > 0){
      mDrivetrain.comboDrive(-mLimelight.getTargetY() * (0.5 / 20.5) - 0.1, mLimelight.getTargetX() * (0.5 / 20.5));
    }
    else{
      mDrivetrain.comboDrive(-mLimelight.getTargetY() * (0.5 / 20.5), mLimelight.getTargetX() * (0.5 / 20.5));
    }

    
  }

  @Override
  public void end(boolean interrupted) {
    mDrivetrain.rawDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
