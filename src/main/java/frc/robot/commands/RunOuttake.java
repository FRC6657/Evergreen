// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Outtake;

public class RunOuttake extends CommandBase {

  private final Outtake mOuttake;
  private final double mSpeed;

  public RunOuttake(Outtake pOuttake, double pSpeed) {

    mSpeed = pSpeed;
    mOuttake = pOuttake;

    addRequirements(mOuttake);
  }

  @Override
  public void execute() {
    mOuttake.run(mSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    mOuttake.run(0);
  }
}
