package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Timer;

public class BallSystemCmd implements cmd {

	// Class Attributes
	CANTalon1989 ballConveyor;
	CANTalon1989 ballOutputWheel;
	JsScaled driveStick;
	Boolean ballIntakeActive;
	Boolean ballOutputActive;
	Timer rampTime = new Timer();
	Timer cheatTimer = new Timer(); 
	int directionCheck = 1;
	boolean systemRunning = false;
	double wheelSpeed = 0;
	Integer state1 = 0;
	int state2 = 0;	
	
	// Constructor: Expects 2 motors and a JoyStick
	public BallSystemCmd(CANTalon1989 ballConveyor, CANTalon1989 ballOutputWheel, JsScaled driveStick){
		this.ballConveyor = ballConveyor;
		this.ballOutputWheel = ballOutputWheel;
		this.driveStick = driveStick;
	}
	
	// Ball Intake Method - Used in Teleop
	public void ballIntake(){
		systemRunning = true;
		ballConveyor.set(1);
		if (state1 == 0){
			rampTime.stop();
			rampTime.reset();
			rampTime.start();
			state1 = 1;
		}
		// Ramp Up
		if (wheelSpeed > -1){
			if(rampTime.get() > 0.25){
				wheelSpeed -= 0.2;
				rampTime.stop();
				rampTime.reset();
				rampTime.start();
			}
		}
		// Update Speed
		ballOutputWheel.set(wheelSpeed);
		}
	
	// Ball Output Method - Used in Teleop
	public void ballOutput(){
		systemRunning = true;
		ballConveyor.set(1);
		if (state2 == 0){
			rampTime.stop();
			rampTime.reset();
			rampTime.start();
			state2 = 1;
		}
		// Ramp Up
		if (wheelSpeed < 1){
			if(rampTime.get() > 0.25){
				wheelSpeed += 0.2;
				rampTime.stop();
				rampTime.reset();
				rampTime.start();
			} 
		}
		// Update Speed
		ballOutputWheel.set(wheelSpeed);
	}
	
	
	
	
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void autonomousPeriodic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		
		}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		ballIntakeActive = false;
		ballOutputActive = false;
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		//Components.writemessage.setmessage(5, ballIntakeActive.toString() );

		// Button 7 - Intake Balls
		if(driveStick.getRawButton(7) == true){
			ballIntake();
		}
		// Button 8 - Output Balls
		else if(driveStick.getRawButton(8) == true){
			ballOutput();		
		} else { // Begin the ramp down
			// Turn off conveyer
			ballConveyor.set(0);
			
			// Update Ball Speed
			ballOutputWheel.set(wheelSpeed);
			
			// If system was running, begin lowering the speed and "turn off" the system.
			if (systemRunning == true){
				rampTime.stop();			
				rampTime.reset();
				rampTime.start(); 
				systemRunning = false;
				
				// Make sure to reset the states
				if (state1 != 0 || state2 != 0){
					state1 = state2 = 0;
				}
			}
			// Ramp the speed up from negative.
			if (wheelSpeed < 0){
				if(rampTime.get() > 0.25){
					if(wheelSpeed + 0.2 <= -0.2){
						wheelSpeed += 0.2;
					}else{
						wheelSpeed = 0;
					}
					rampTime.stop();
					rampTime.reset();
					rampTime.start();
				}
			}
			
			// Ramp the speed down from positive
			if (wheelSpeed > 0){
				if(rampTime.get() > 0.25){
					if(wheelSpeed - 0.2 >= .2){
						wheelSpeed -= 0.2;
					}else{
						wheelSpeed = 0;
					}
					rampTime.stop();
					rampTime.reset();
					rampTime.start();
				}
			}
		}	
	}
}