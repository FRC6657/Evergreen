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
        Boolean firstRun = true;

        @SuppressWarnings("unused")
        public PDP() {

                mPDP = new PowerDistributionPanel(0);

                
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

        public double getMaxLiftAmp() {
                if (firstRun) {
                        firstRun = false;
                        Lift.setMaxAmp(0);
                }
                if (Math.abs(getCurrent(13)) > Lift.getMaxAmp()) {
                        Lift.setMaxAmp(getCurrent(13));
                }
                return Lift.getMaxAmp();
        }
}
