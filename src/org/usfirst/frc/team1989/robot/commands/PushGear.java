package org.usfirst.frc.team1989.robot.commands;

import org.usfirst.frc.team1989.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class PushGear extends Command {
	
	private boolean done;
	
	public PushGear() {
		
	}
	
	protected void initialize() {
		done = false;
    	Robot.gearPusher.gearPushStart();
   
    }
    
    protected void execute() {
    	if (Robot.gearPusher.pushRoutine == true ){
			Robot.gearPusher.gearPush();
			if (Robot.gearPusher.pushRoutine == false){
				done = true;
			}
    	}
    }
    
    protected boolean isFinished() {
    	return done;
    }

    protected void end() {
    	Robot.mDrive.resetEncoders();
    }

    protected void interrupted() {
    	Robot.mDrive.resetEncoders();
    }

}
