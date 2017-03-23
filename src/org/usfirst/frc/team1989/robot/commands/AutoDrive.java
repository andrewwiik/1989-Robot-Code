package org.usfirst.frc.team1989.robot.commands;

import org.usfirst.frc.team1989.robot.Components;
import org.usfirst.frc.team1989.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDrive extends Command {

	private double inches, voltage;
	private boolean watchLimitSwitch, toggleTalonBreaks, killVoltage;
	
	public AutoDrive(double inchesValue, double voltageValue, boolean watchLimitSwitchValue, boolean toggleTalonBrakesValue, boolean killVoltageValue) {
		inches = inchesValue;
		voltage = voltageValue;
		watchLimitSwitch = watchLimitSwitchValue;
		toggleTalonBreaks = toggleTalonBrakesValue;
		killVoltage = killVoltageValue;
	}
	protected void initialize() {
    	Robot.mDrive.resetEncoders();
    	Robot.mDrive.toggleBreaks(toggleTalonBreaks);
    }
    
    protected void execute() {
    	Robot.mDrive.driveStick.pY = voltage;
    }
    
    protected boolean isFinished() {
    	boolean done = false;
    	if (inches >= 0) {
    		done = (Math.abs(Robot.mDrive.getLeftEncoderValue()) * 73 > inches);
    	} else {
    		done = (Math.abs(Robot.mDrive.getLeftEncoderValue()) * 73 < inches);
    	}
    	
    	if (watchLimitSwitch) {
    		if (Components.gearLimit.get() == true) {
    			done = true;
    		}
    	}
    	return done;
    }

    protected void end() {
    	if (killVoltage) {
    		Robot.mDrive.driveStick.pY = 0.0;
    	}
    	Robot.mDrive.toggleBreaks(false);
    }

    protected void interrupted() {
    	if (killVoltage) {
    		Robot.mDrive.driveStick.pY = 0.0;
    	}
    	Robot.mDrive.toggleBreaks(false);
    }

}
