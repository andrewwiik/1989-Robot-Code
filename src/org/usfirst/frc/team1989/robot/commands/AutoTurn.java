package org.usfirst.frc.team1989.robot.commands;

import org.usfirst.frc.team1989.robot.Robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;

public class AutoTurn extends Command {

	private final double diagonalOfRobot = 36.67;
	private double inches, angle, voltage;
	private boolean talonBrakingToggle;
	private ADXRS450_Gyro gyro;
	private static final double threshold = 2;
	
	public AutoTurn(double angle, double voltage, boolean talonBrakingToggle) {
		this.angle = angle;
		this.voltage = voltage;
		if (this.angle < 0) {
			this.voltage = -this.voltage;
		}
		
		this.talonBrakingToggle = talonBrakingToggle;
		inches = Math.abs(diagonalOfRobot*Math.PI*this.angle/360.0);
		
		this.gyro = new ADXRS450_Gyro();
	}
	protected void initialize() {
    	Robot.mDrive.resetEncoders();
    	Robot.mDrive.toggleBreaks(talonBrakingToggle);
    	this.gyro.reset();
    }
    
    protected void execute() {
    	Robot.mDrive.driveStick.pTwist = voltage;
    }
    
    protected boolean isFinished() {
    	double offset = Math.abs(this.angle - this.gyro.getAngle());
        return (offset < threshold);
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
