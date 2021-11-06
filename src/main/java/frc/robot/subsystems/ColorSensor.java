// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

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

  private SuppliedValueWidget<Boolean> mWidgetCurrentColor = Shuffleboard.getTab("Color").addBoolean("Current Color", () -> true);
  private SuppliedValueWidget<Boolean> mWidgetEstimatedColor = Shuffleboard.getTab("Color").addBoolean("Estimated Color", () -> true);

  public ColorSensor() {

    mColorSensor = new ColorSensorV3(Constants.kColorSensorPort);

    mColorMatcher.addColorMatch(kRedTarget);
    mColorMatcher.addColorMatch(kGreenTarget);
    mColorMatcher.addColorMatch(kBlueTarget);
    mColorMatcher.addColorMatch(kYellowTarget);

  }

  public Color getColor() {
    return mColorSensor.getColor();
  }

  @Override
  public void periodic() {

    ColorMatchResult mMatch = mColorMatcher.matchClosestColor(mColorSensor.getColor());

    if (mMatch.color == kRedTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "blue"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "red"));
    } else if (mMatch.color == kGreenTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "yellow"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "green"));
    } else if (mMatch.color == kBlueTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "red"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "blue"));
    } else if (mMatch.color == kYellowTarget) {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "green"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "yellow"));
    } else {
      mWidgetCurrentColor.withProperties(Map.of("colorWhenTrue", "black"));
      mWidgetEstimatedColor.withProperties(Map.of("colorWhenTrue", "black"));
    }

  }
}
