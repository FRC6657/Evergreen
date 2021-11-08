// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeCamera;

public class changeCamera extends CommandBase {
  
  //

  private final IntakeCamera mIntakeCamera;

  public changeCamera(IntakeCamera pIntakeCamera) {
    mIntakeCamera = pIntakeCamera;
    addRequirements(mIntakeCamera);
  }

  @Override
  public void initialize() {
    mIntakeCamera.changeView();
  }
}
