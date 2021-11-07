// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Limelight;

public class ToggleVision extends CommandBase {

  private final Limelight mLimelight;

  public ToggleVision(Limelight pLimelight) {
    mLimelight = pLimelight;
    addRequirements(mLimelight);
  }

  @Override
  public void initialize() {
    mLimelight.toggleVision();
  }
}
