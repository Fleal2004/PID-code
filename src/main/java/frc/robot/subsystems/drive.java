/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class drive extends Subsystem {

public static TalonSRX m_wheel; 

public drive (){
  
  m_wheel = new TalonSRX(0);
}

public double getPosition(){
  return m_wheel.getSelectedSensorPosition();

}

public void setPower(double power){
  m_wheel.set(ControlMode.PercentOutput, power);
}

@Override
  public void initDefaultCommand() {


    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
