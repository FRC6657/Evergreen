// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.changeCamera;
import frc.robot.commands.runAgitator;
import frc.robot.subsystems.Agitator;
import frc.robot.subsystems.Blinkin;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeCamera;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.PDP;

public class RobotContainer {

  private final Agitator mAgitator = new Agitator();
  private final Blinkin mBlinkin = new Blinkin();
  private final ColorSensor mColorSensor = new ColorSensor();
  private final ControlPanel mControlPanel = new ControlPanel();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Intake mIntake = new Intake();
  private final IntakeCamera mIntakeCamera = new IntakeCamera();
  private final Lift mLift = new Lift();
  private final Limelight mLimelight = new Limelight();
  private final PDP mPDP = new PDP();

  private final XboxController mController = new XboxController(0);

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    final JoystickButton mA = new JoystickButton(mController, XboxController.Button.kA.value);

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
