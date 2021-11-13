// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.cscore.HttpCamera;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {

  private final Agitator mAgitator = new Agitator();
  private final ColorSensor mColorSensor = new ColorSensor();
  private final ControlPanel mControlPanel = new ControlPanel();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Intake mIntake = new Intake();
  private final Lift mLift = new Lift();
  private final Limelight mLimelight = new Limelight();
  private final Outtake mOuttake = new Outtake();

  private final Joystick mJoystick = new Joystick(0);
  private final XboxController mController = new XboxController(1);

  private final UsbCamera mIntakeCamera = CameraServer.getInstance().startAutomaticCapture();
  private final HttpCamera mLimelightCamera = new HttpCamera("Limelight", "http://10.66.57.11:5800/");

  private final SendableChooser<Command> mAutoChooser = new SendableChooser<>();

  private ShuffleboardTab mDriverstation = Shuffleboard.getTab("Driver Station");

  public RobotContainer() {
    configureButtonBindings();
  }

  private class BaseLine extends SequentialCommandGroup{
    public BaseLine(){
      addCommands(
        new AutonomousDrive(mDrivetrain, 0.4, -0.4).withTimeout(1)
      );
    }
  }

  private class SeekAimShoot extends SequentialCommandGroup{
    public SeekAimShoot(){
      addCommands(
        new SeekTarget(mLimelight, mDrivetrain),
        new AutoTarget(mLimelight, mDrivetrain).withTimeout(3),
        new RunOuttake(mOuttake, 0.9).withTimeout(1.5),
        new RunIntake(mIntake, 1).withTimeout(0.75),
        new RunIntake(mIntake, -1).withTimeout(0.05),
        new RunAgitator(mAgitator, -0.7).withTimeout(0.5),
        new RunAgitator(mAgitator, 0.7).withTimeout(0.5),
        new RunAgitator(mAgitator, 0).withTimeout(0.5),
        new RunOuttake(mOuttake, 0.9).withTimeout(1.5),
        new RunIntake(mIntake, 1).withTimeout(0.75),
        new RunIntake(mIntake, -1).withTimeout(0.05),
        new RunAgitator(mAgitator, 0.7).withTimeout(0.5),
        new RunAgitator(mAgitator, -0.7).withTimeout(0.5),
        new RunAgitator(mAgitator, 0).withTimeout(1),
        new RunOuttake(mOuttake, 0.9).withTimeout(1.5)
      );
    }
  }

  @SuppressWarnings("unused")
  private void configureButtonBindings() {

    LiveWindow.disableAllTelemetry();

    CommandScheduler.getInstance()
      .setDefaultCommand(mDrivetrain,
        new DriverControl(
          mDrivetrain,
          () -> cubicDeadband(mJoystick.getRawAxis(1), 1, 0.1),
          () -> -cubicDeadband(mJoystick.getRawAxis(2), 1, 0.1),
          () -> mJoystick.getRawAxis(3),
          () -> mJoystick.getRawButton(1)
        )
      );

    //Joystick Buttons
    POVButton mJoystickHatRight = new POVButton(mJoystick, 90);
    POVButton mJoystickHatUp = new POVButton(mJoystick, 0);
    POVButton mJoystickHatLeft = new POVButton(mJoystick, 270);
    POVButton mJoystickHatDown = new POVButton(mJoystick, 180);

    JoystickButton mSide = new JoystickButton(mJoystick, 2);
    JoystickButton mBottomLeft = new JoystickButton(mJoystick, 3);
    JoystickButton mBottomRight = new JoystickButton(mJoystick, 4);
    JoystickButton mTopLeft = new JoystickButton(mJoystick, 5);
    JoystickButton mTopRight = new JoystickButton(mJoystick, 6);
    JoystickButton m7 = new JoystickButton(mJoystick, 7);
    JoystickButton m8 = new JoystickButton(mJoystick, 8);
    JoystickButton m9 = new JoystickButton(mJoystick, 9);
    JoystickButton m10 = new JoystickButton(mJoystick, 10);
    JoystickButton m11 = new JoystickButton(mJoystick, 11);
    JoystickButton m12 = new JoystickButton(mJoystick, 12);

    //Controller Buttons
    POVButton mControllerDPadRight = new POVButton(mController, 90);
    POVButton mControllerDPadUp = new POVButton(mController, 0);
    POVButton mControllerDPadLeft = new POVButton(mController, 270);
    POVButton mControllerDPadDown = new POVButton(mController, 180);

    JoystickButton mA = new JoystickButton(mController, 1);
    JoystickButton mB = new JoystickButton(mController, 2);
    JoystickButton mX = new JoystickButton(mController, 3);
    JoystickButton mY = new JoystickButton(mController, 4);
    JoystickButton mLeftBumper = new JoystickButton(mController, 5);
    JoystickButton mRightBumper = new JoystickButton(mController, 6);
    JoystickButton mMiddleLeft = new JoystickButton(mController, 7);
    JoystickButton mMiddleRight = new JoystickButton(mController, 8);
    JoystickButton mLeftStickPress = new JoystickButton(mController, 9);
    JoystickButton mRightStickPress = new JoystickButton(mController, 10);

    //Joystick Bindings
    mSide.whenHeld(new RunIntake(mIntake, Constants.kIntakeSpeed));
    mBottomRight.whenHeld(new RunLift(mLift, Constants.kLiftSpeed));
    mTopRight.whenHeld(new RunLift(mLift, -Constants.kLiftSpeed));
    mTopLeft.whenHeld(new RunAgitator(mAgitator, -Constants.kAgitatorSpeed));
    mBottomLeft.whenHeld(new RunAgitator(mAgitator, Constants.kAgitatorSpeed));

    m7.whenPressed(new MatchColor(mControlPanel, mColorSensor, Constants.kControlPanelSpinSpeed)
      .withInterrupt(() -> (mJoystickHatLeft.get() || mJoystickHatRight.get())));
    m8.whenHeld(new AutoTarget(mLimelight, mDrivetrain));
    m9.whenPressed(new RunIntake(mIntake, -Constants.kIntakeSpeed)
      .withTimeout(0.1));
    
    mJoystickHatUp.whenHeld(new RunPivot(mControlPanel, -Constants.ControlPanelPivotSpeed));
    mJoystickHatDown.whenHeld(new RunPivot(mControlPanel, Constants.ControlPanelPivotSpeed));
    mJoystickHatRight.whenHeld(new RunSpinner(mControlPanel, Constants.kControlPanelSpinSpeed));

    //Controller Bindings
    mA.whenPressed(new RunIntake(mIntake, -Constants.kIntakeSpeed)
      .withTimeout(0.1));
    mB.whenHeld(new AutoTarget(mLimelight, mDrivetrain));
    mY.whenPressed(new MatchColor(mControlPanel, mColorSensor, Constants.kControlPanelSpinSpeed)
      .withInterrupt(() -> (mJoystickHatLeft.get() || mJoystickHatRight.get())));
    mRightBumper.whenHeld(new RunOuttake(mOuttake, Constants.kOuttakeSpeed));

    mControllerDPadLeft.whenHeld(new RunAgitator(mAgitator, -Constants.kAgitatorSpeed));
    mControllerDPadRight.whenHeld(new RunAgitator(mAgitator, Constants.kAgitatorSpeed));

    mAutoChooser.setDefaultOption("Base Line", new BaseLine());
    mAutoChooser.addOption("Seek Aim", new SeekAimShoot());

    mDriverstation.add("Autonomous Select", mAutoChooser).withPosition(2, 4).withSize(2, 1);   
    mDriverstation.add("Intake Camera",mIntakeCamera).withPosition(10, 0).withSize(3,4);
    mDriverstation.add("Limelight",mLimelightCamera).withPosition(6, 0).withSize(4,4); 

  }

  private double cubicDeadband(double pInput, double pWeight, double pDeadband){

    double mOutput;

    if(Math.abs(pInput) > pDeadband){
      mOutput = (((pWeight * (Math.pow(pInput, 3)) + 1*(1 - pWeight) * pInput) - (Math.abs(pInput)) / pInput * (pWeight * (Math.pow(pDeadband, 3)) + (1 - pWeight) * pDeadband)) / (1 - (pWeight * (Math.pow(pDeadband, 3)) + (1 - pWeight) * pDeadband)));
    }
    else{
      mOutput = 0;
    }
    return mOutput;
  }

  public Command getAutonomousCommand() {
    return mAutoChooser.getSelected();
  }
}
