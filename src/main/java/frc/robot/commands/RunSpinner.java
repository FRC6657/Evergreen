// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

public class RunSpinner extends CommandBase {

  private final ControlPanel mControlPanel;
  private final ColorSensor mColorSensor;
  private final double mSpeed;

  public RunSpinner(ControlPanel pControlPanel, ColorSensor pColorSensor, double pSpeed) {

    mSpeed = pSpeed;
    mControlPanel = pControlPanel;
    mColorSensor = pColorSensor;

    addRequirements(mControlPanel);
  }

  @Override
  public void execute() {
    mControlPanel.spin(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mControlPanel.spin(0);
  }
}
