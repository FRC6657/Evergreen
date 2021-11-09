// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Blinkin;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;

public class LightIndicator extends CommandBase {

  private final Blinkin mBlinkin;
  private final ColorSensor mColorSensor;

  public LightIndicator(Blinkin pBlinkin, ColorSensor pColorSensor) {

    mBlinkin = pBlinkin;
    mColorSensor = pColorSensor;
    addRequirements(mColorSensor);
  }

  @Override
  public void execute() {
    mBlinkin.set(mColorSensor.getEstimatedColor());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return (mColorSensor.getEstimatedColor() == mColorSensor.getGameData());
  }
}
