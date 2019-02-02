/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.constants.flywheelconstants;
public class TurnDegrees extends Command {
  
  public double m_error; 
  public double m_output;
  public double m_DesiredDistance;
  public double m_CurrentTime;
  public double m_olderror;
  public double m_derivative; 
  public double m_OldTime;
  
  
  public TurnDegrees( double distance ) {
    m_DesiredDistance = distance;
    requires(Robot.m_drive);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  m_OldTime = Timer.getFPGATimestamp();
  m_olderror = m_DesiredDistance;


}


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_CurrentTime = Timer.getFPGATimestamp();
    m_error = m_DesiredDistance - Robot.m_drive.getPosition();
    m_derivative = (m_error - m_olderror) / (m_CurrentTime - m_OldTime);
    m_output = m_error * flywheelconstants.kP + m_derivative * flywheelconstants.kD; 
    Robot.m_drive.setPower(m_output); 
    m_olderror = m_error;
    m_OldTime = m_CurrentTime; 
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(m_CurrentTime) < flywheelconstants.tolerance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drive.setPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
