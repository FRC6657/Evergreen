// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lift;

public class RunLift extends CommandBase {

  private final Lift mLift;
  private final double mSpeed;

  public RunLift(Lift pLift, double pSpeed) {

    mSpeed = pSpeed;
    mLift = pLift;

    addRequirements(mLift);
  }

  @Override
  public void execute() {
    mLift.run(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mLift.run(0);
  }
}
