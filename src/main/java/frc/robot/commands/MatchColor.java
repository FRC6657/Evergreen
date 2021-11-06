// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

public class MatchColor extends CommandBase {
  
  private final ControlPanel mControlPanel;
  private final ColorSensor mColorSensor;
  private double mSpeed;

  public MatchColor(ControlPanel pControlPanel, ColorSensor pColorSensor, double pSpeed) {

    mControlPanel = pControlPanel;
    mColorSensor = pColorSensor;
    mSpeed = pSpeed;

    addRequirements(mControlPanel,mColorSensor);
  }

  @Override
  public void execute() {
    mControlPanel.spin(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mControlPanel.spin(0);
  }

  @Override
  public boolean isFinished() {
    return (mColorSensor.getEstimatedColor() == mColorSensor.getGameData());
  }
}
