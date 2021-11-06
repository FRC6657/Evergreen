// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Agitator;

public class RunAgitator extends CommandBase {

  private final Agitator mAgitator;
  private final double mSpeed;

  public RunAgitator(Agitator pAgitator, double pSpeed) {

    mSpeed = pSpeed;
    mAgitator = pAgitator;

    addRequirements(mAgitator);
  }

  @Override
  public void execute() {
    mAgitator.run(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mAgitator.run(0);
  }
}
