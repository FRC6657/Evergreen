// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Agitator;

public class runAgitator extends CommandBase {
  
  private final Agitator mAgitator;
  private final double mSpeed;

  public runAgitator(Agitator pAgitator, double pSpeed) {
    
    mSpeed =  pSpeed;
    mAgitator = pAgitator;

    addRequirements(mAgitator);
  }

  @Override
  public void initialize(){}

  @Override
  public void execute() {
    mAgitator.run(mSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    mAgitator.run(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
