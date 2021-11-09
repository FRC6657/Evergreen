// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.I2C;

public final class Constants {
    //IDS
    public final static int kPDPID = 0;
    public final static int kFrontLeft = 1;
    public final static int kBackLeft = 2;
    public final static int kFrontRight = 3;
    public final static int kBackRight = 4;
    public final static int kIntakeID = 5;
    public final static int kLiftID = 6;
    public final static int kOuttakeLeft = 7;
    public final static int kOuttakeRight = 8;
    public final static int kAgitatorID = 9;
    public final static int kControlPanelPivot = 10;
    public final static int kControlPanelSpin = 11; 

    public final static int kIntakeCamPWM = 6;
    public final static int kBlinkinPWM = 5;
    public final static int kGatePWM = 9;

    public final static I2C.Port kColorSensorPort = I2C.Port.kOnboard;

    //Speeds
    public final static double kOuttakeSpeed = 0.8;
    public final static double kIntakeSpeed = 0.4;
    public final static double kLiftSpeed = 1;
	public final static double kControlPanelSpinSpeed= 0.2;
	public final static double kAgitatorSpeed = 1;
	public final static double ControlPanelPivotSpeed = 0.3;
	public final static double ControlPanelSpinSpeed = 0.4;

}
