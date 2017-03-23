package org.usfirst.frc.team1989.robot;


// This class is specifically for the testing of encoders.  Once the distance test is complete, remove it from Robot.java
// Code to run the test is in the test mode.
public class EncoderDistanceCMD implements cmd{

	CANTalon1989 driveBackLeft;
	CANTalon1989 driveBackRight;
	JsScaled driveStick;
	public EncoderDistanceCMD(CANTalon1989 driveBackLeft, CANTalon1989 driveBackRight, JsScaled driveStick){
		this.driveStick = driveStick;
		this.driveBackLeft = driveBackLeft;
		this.driveBackRight = driveBackRight;
		
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
		driveBackLeft.setEncPosition(0);
		driveBackRight.setEncPosition(0);
	}

	@Override
	public void testPeriodic() {
		// TODO Auto-generated method stub
		int average = (driveBackLeft.getEncPosition() + driveBackRight.getEncPosition()) / 2; 
		if(average < 100){
			
			driveStick.pY = 1.0;
		}
	}

	@Override
	public void teleopInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		
	}

}
