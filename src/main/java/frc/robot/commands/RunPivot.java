// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class RunPivot extends CommandBase {

  private final ControlPanel mControlPanel;
  private final double mSpeed;

  public RunPivot(ControlPanel pControlPanel, double pSpeed) {

    mSpeed = pSpeed;
    mControlPanel = pControlPanel;

    addRequirements(mControlPanel);
  }

  @Override
  public void execute() {
    mControlPanel.pivot(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mControlPanel.pivot(0);
  }
}
