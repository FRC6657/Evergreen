// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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
  private final Blinkin mBlinkin = new Blinkin();
  private final ColorSensor mColorSensor = new ColorSensor();
  private final ControlPanel mControlPanel = new ControlPanel();
  private final Drivetrain mDrivetrain = new Drivetrain();
  private final Intake mIntake = new Intake();
  private final IntakeCamera mIntakeCamera = new IntakeCamera();
  private final Lift mLift = new Lift();
  private final Limelight mLimelight = new Limelight();
  private final Outtake mOuttake = new Outtake();
  private final PDP mPDP = new PDP();

  private final Joystick mJoystick = new Joystick(0);
  private final XboxController mController = new XboxController(1);

  private final SendableChooser<Command> mAutoChooser = new SendableChooser<>();

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

  private class SeekAim extends SequentialCommandGroup{
    public SeekAim(){
      addCommands(
        new SeekTarget(mLimelight, mDrivetrain),
        new AutoTarget(mLimelight, mDrivetrain)
      );
    }
  }

  @SuppressWarnings("unused")
  private void configureButtonBindings() {

    CommandScheduler.getInstance()
      .setDefaultCommand(mDrivetrain,
        new DriverControl(
          mDrivetrain,
          () -> cubicDeadband(mJoystick.getRawAxis(Joystick.AxisType.kY.value), 1, 0.1),
          () -> cubicDeadband(mJoystick.getRawAxis(Joystick.AxisType.kTwist.value), 1, 0.1)
        )
      );

    //Joystick Buttons
    POVButton mJoystickHatRight = new POVButton(mJoystick, 90);
    POVButton mJoystickHatUp = new POVButton(mJoystick, 0);
    POVButton mJoystickHatLeft = new POVButton(mJoystick, 270);
    POVButton mJoystickHatDown = new POVButton(mJoystick, 180);

    JoystickButton mTrigger = new JoystickButton(mJoystick, 1);
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

    //TODO: Test all of these

    //Joystick Bindings
    mTrigger.whenHeld(new RunOuttake(mOuttake, Constants.kOuttakeSpeed));
    mSide.whenHeld(new RunIntake(mIntake, Constants.kIntakeSpeed));
    //TODO: Get a correct height value/check up&down polarity
    mBottomLeft.whenPressed(new LiftToHeight(mLift, mPDP, Constants.kLiftSpeed, 30)
      .withInterrupt(() -> (mBottomLeft.get() || mTopRight.get())));
    mBottomRight.whenHeld(new RunLift(mLift, mPDP, -Constants.kLiftSpeed));
    mTopLeft.whenPressed(new LiftToHeight(mLift, mPDP, Constants.kLiftSpeed, 0)
      .withInterrupt(() -> (mBottomLeft.get() || mTopRight.get())));
    mTopRight.whenHeld(new RunLift(mLift, mPDP, Constants.kLiftSpeed));
    m7.whenPressed(new MatchColor(mControlPanel, mBlinkin, mColorSensor, Constants.kControlPanelSpinSpeed)
      .withInterrupt(() -> (mJoystickHatLeft.get() || mJoystickHatRight.get())));
    m8.whenHeld(new AutoTarget(mLimelight, mDrivetrain));
    m9.whenPressed(new RunIntake(mIntake, -Constants.kIntakeSpeed)
      .withTimeout(0.1));
    m11.whenHeld(new RunAgitator(mAgitator, -Constants.kAgitatorSpeed));
    m12.whenHeld(new RunAgitator(mAgitator, Constants.kAgitatorSpeed));

    mJoystickHatUp.whenHeld(new RunPivot(mControlPanel, -Constants.ControlPanelPivotSpeed));
    mJoystickHatDown.whenHeld(new RunPivot(mControlPanel, Constants.ControlPanelPivotSpeed));
    mJoystickHatLeft.whenHeld(new RunSpinner(mControlPanel, mBlinkin, mColorSensor, -Constants.ControlPanelSpinSpeed));
    mJoystickHatRight.whenHeld(new RunSpinner(mControlPanel, mBlinkin, mColorSensor, Constants.ControlPanelSpinSpeed));

    //Controller Bindings
    mA.whenPressed(new RunIntake(mIntake, -Constants.kIntakeSpeed)
      .withTimeout(0.1));
    mB.whenHeld(new AutoTarget(mLimelight, mDrivetrain));
    mY.whenPressed(new MatchColor(mControlPanel, mBlinkin, mColorSensor, Constants.kControlPanelSpinSpeed)
      .withInterrupt(() -> (mJoystickHatLeft.get() || mJoystickHatRight.get())));
    mRightBumper.whenHeld(new RunOuttake(mOuttake, Constants.kOuttakeSpeed));

    mControllerDPadUp.whenPressed(new ChangeCamera(mIntakeCamera));
    mControllerDPadLeft.whenHeld(new RunAgitator(mAgitator, -Constants.kAgitatorSpeed));
    mControllerDPadRight.whenHeld(new RunAgitator(mAgitator, Constants.kAgitatorSpeed));

    mAutoChooser.setDefaultOption("Base Line", new BaseLine());
    mAutoChooser.addOption("Seek Aim", new SeekAim());

  }

  private double cubicDeadband(double pInput, double pWeight, double pDeadband){

    double output;

    if(Math.abs(pInput) > pDeadband){
      output = (((pWeight * (Math.pow(pInput, 3)) + 1*(1 - pWeight) * pInput) - (Math.abs(pInput)) / pInput * (pWeight * (Math.pow(pDeadband, 3)) + (1 - pWeight) * pDeadband)) / (1 - (pWeight * (Math.pow(pDeadband, 3)) + (1 - pWeight) * pDeadband)));
    }
    else{
      output = 0;
    }
    return output;
  }

  public Command getAutonomousCommand() {
    return mAutoChooser.getSelected();
  }
}
