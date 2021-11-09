// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SuppliedValueWidget;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {

  private final ColorSensorV3 mColorSensor;

  private final ColorMatch mColorMatcher = new ColorMatch();

  // TODO: Retune
  private final Color kRedTarget = ColorMatch.makeColor(.533, .338, .128);
  private final Color kGreenTarget = ColorMatch.makeColor(.167, .578, .256);
  private final Color kBlueTarget = ColorMatch.makeColor(.125, .422, .455);
  private final Color kYellowTarget = ColorMatch.makeColor(.316, .561, .122);

  private SuppliedValueWidget<Boolean> mWidgetCurrentColor = Shuffleboard.getTab("Color")
      .addBoolean("Current Color", () -> true).withPosition(0, 1);
  private SuppliedValueWidget<Boolean> mWidgetEstimatedColor = Shuffleboard.getTab("Color")
      .addBoolean("Estimated Color", () -> true).withPosition(1, 1);

  private SuppliedValueWidget<Double> mWidgetR = Shuffleboard.getTab("Color").addNumber("R Value", () -> getRedValue());
  private SuppliedValueWidget<Double> mWidgetG = Shuffleboard.getTab("Color").addNumber("G Value", () -> getGreenValue());
  private SuppliedValueWidget<Double> mWidgetB = Shuffleboard.getTab("Color").addNumber("B Value", () -> getBlueValue());

  private String mEstimatedColorString = "Not Set";
  private String mObservedColorString = "Not Set";

  public ColorSensor() {

    mColorSensor = new ColorSensorV3(Constants.kColorSensorPort);

    mColorMatcher.addColorMatch(kRedTarget);
    mColorMatcher.addColorMatch(kGreenTarget);
    mColorMatcher.addColorMatch(kBlueTarget);
    mColorMatcher.addColorMatch(kYellowTarget);

  }

  public String getEstimatedColor() {
    return mEstimatedColorString;
  }

  public String getObservedColor() {
    return mObservedColorString;
  }

  public int getRedValue() {
    return mColorSensor.getRed();
  }

  public int getGreenValue() {
    return mColorSensor.getGreen();
  }

  public int getBlueValue() {
    return mColorSensor.getRed();
  }

  public String getGameData() {
    String mRawData = DriverStation.getInstance().getGameSpecificMessage();
    if (mRawData.length() > 0) {
      switch (mRawData.charAt(0)) {
        case 'R':
          return "Red";
        case 'G':
          return "Blue";
        case 'B':
          return "Blue";
        case 'Y':
          return "Yellow";
        default:
          return "Broken Data";
      }
    } else {
      return "No Data";
    }
  }

  @Override
  public void periodic() {

    ColorMatchResult mMatch = mColorMatcher.matchClosestColor(mColorSensor.getColor());

    if (mMatch.color == kRedTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "red"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "blue"));
      mEstimatedColorString = "Blue";
      mObservedColorString = "Red";
    } else if (mMatch.color == kGreenTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "green"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "yellow"));
      mEstimatedColorString = "Yellow";
      mObservedColorString = "Green";
    } else if (mMatch.color == kBlueTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "blue"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "red"));
      mEstimatedColorString = "Red";
      mObservedColorString = "Blue";
    } else if (mMatch.color == kYellowTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "yellow"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "green"));
      mEstimatedColorString = "Green";
      mObservedColorString = "Yellow";
    } else {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "black"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "black"));
      mEstimatedColorString = "None";
      mObservedColorString = "None";
    }
  }
}
