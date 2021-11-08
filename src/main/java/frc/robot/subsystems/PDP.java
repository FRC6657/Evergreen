// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PDP extends SubsystemBase {

    private PowerDistributionPanel mPDP;

    // TODO: Index each motors channel

    @SuppressWarnings("unused")
    public PDP() {

        mPDP = new PowerDistributionPanel(0);

        SuppliedValueWidget<Double> mAgitatorCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Agitator", () -> getCurrent(0))
                .withPosition(0, 0)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mBlinkinCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Blinkin", () -> getCurrent(0))
                .withPosition(2, 0)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mControlPanelCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Control Panel", () -> getCurrent(0))
                .withPosition(0, 1)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mDrivetrainCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Drivetrain", () -> (getCurrent(0) + getCurrent(0) + getCurrent(0) + getCurrent(0)))
                .withPosition(2, 1)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mIntakeCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Intake", () -> getCurrent(0))
                .withPosition(0, 2)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mLiftCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Lift", () -> getCurrent(0))
                .withPosition(2, 2)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mLimelightCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Limelight", () -> getCurrent(0))
                .withPosition(0, 3)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mOuttakeCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Outtake", () -> (getCurrent(0) + getCurrent(0)))
                .withPosition(2, 3)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mTotalCurrent = Shuffleboard.getTab("PDP")
                .addNumber("Total Current", () -> getTotalCurrent())
                .withPosition(4, 0)
                .withSize(2, 1);
        SuppliedValueWidget<Double> mBatteryVoltage = Shuffleboard.getTab("PDP")
                .addNumber("Battery Voltage", () -> getBatteryVoltage())
                .withPosition(4, 1)
                .withSize(2, 1);

    }

    public double getBatteryVoltage() {
        return mPDP.getVoltage();
    }

    public double getTotalCurrent() {
        return mPDP.getTotalCurrent();
    }

    public double getCurrent(int pChannel) {
        return mPDP.getCurrent(pChannel);
    }
}
