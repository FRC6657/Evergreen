// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeCamera extends SubsystemBase {

  private Servo mServo;

  // 2 Variables for Shuffleboard reasons
  private static boolean mViewingIntake = false;

  public IntakeCamera() {

    mServo = new Servo(Constants.kIntakeCamPWM);
    mServo.setAngle(120);

  }

  public void changeView() {

    System.out.println("Change View Ran");
    if(getViewingIntake()){
      mServo.setAngle(15);
      setViewingIntake(!getViewingIntake());
    }
    else {
      mServo.setAngle(135);
      setViewingIntake(!getViewingIntake());
    }

  }

  public static boolean getViewingIntake() {
    return mViewingIntake;
  }

  public static void setViewingIntake(boolean inVar) {
    mViewingIntake = inVar;
  }
}
