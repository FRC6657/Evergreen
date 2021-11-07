// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Blinkin;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

public class RunSpinner extends CommandBase {

  private final ControlPanel mControlPanel;
  private final Blinkin mBlinkin;
  private final ColorSensor mColorSensor;
  private final double mSpeed;

  public RunSpinner(ControlPanel pControlPanel, Blinkin pBlinkin, ColorSensor pColorSensor, double pSpeed) {

    mSpeed = pSpeed;
    mControlPanel = pControlPanel;
    mBlinkin = pBlinkin;
    mColorSensor = pColorSensor;

    addRequirements(mControlPanel);
  }

  @Override
  public void execute() {
    mControlPanel.spin(mSpeed);
    mBlinkin.set(mColorSensor.getEstimatedColor());
  }

  @Override
  public void end(boolean interrupted) {
    mControlPanel.spin(0);
  }
}
