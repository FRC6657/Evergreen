// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeCamera extends SubsystemBase {
  
  private Servo mServo;

  //2 Variables for Shuffleboard reasons
  private boolean mViewingIntake = false;

  public IntakeCamera() {
    
    mServo = new Servo(Constants.kIntakeCamPWM);
    changeView();

  }

  public void changeView(){

    if(mViewingIntake){
      mViewingIntake = false;
      mServo.setAngle(135);
    }
    else{
      mViewingIntake = true;
      mServo.setAngle(15);
    }

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
