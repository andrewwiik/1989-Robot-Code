package org.usfirst.frc.team1989.robot.commands;

import org.usfirst.frc.team1989.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoTurn extends Command {

	private final double diagonalOfRobot = 36.67;
	private double inches, angle, voltage;
	private boolean talonBrakingToggle;
	
	public AutoTurn(double angle, double voltage, boolean talonBrakingToggle) {
		this.angle = angle;
		this.voltage = voltage;
		if (this.angle < 0) {
			this.voltage = -this.voltage;
		}
		
		this.talonBrakingToggle = talonBrakingToggle;
		inches = Math.abs(diagonalOfRobot*Math.PI*this.angle/360.0);
	}
	protected void initialize() {
    	Robot.mDrive.resetEncoders();
    	Robot.mDrive.toggleBreaks(talonBrakingToggle);
    }
    
    protected void execute() {
    	Robot.mDrive.driveStick.pTwist = voltage;
    }
    
    protected boolean isFinished() {
    	boolean done = false;
    	if (inches >= 0) {
    		done = (Math.abs(Robot.mDrive.getLeftEncoderValue()) * 73 > inches);
    	} else {
    		done = (Math.abs(Robot.mDrive.getLeftEncoderValue()) * 73 < inches);
    	}
    	return done;
    }

    protected void end() {
    	Robot.mDrive.driveStick.pTwist = 0.0;
    	Robot.mDrive.toggleBreaks(false);
    }

    protected void interrupted() {
    	Robot.mDrive.driveStick.pTwist = 0.0;
    	Robot.mDrive.toggleBreaks(false);
    }

}
