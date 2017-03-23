package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Servo;

public class CameraControl implements cmd {
	Servo servoX;
	Servo servoY;
	double sxValue = 0;
	double syValue = 0;
	JsScaled uStick;
	
	
	public CameraControl(Servo servoX, Servo servoY, JsScaled uStick ){
		this.servoX = servoX;
		this.servoY = servoY;
		this.uStick = uStick;
	}
	
	public void cameraMovement(){
		if(uStick.getPOV(0) == 270){
			sxValue -= 0.025;			
		}
		else if (uStick.getPOV(0) == 90){
			sxValue += 0.025;
		}
		else if (uStick.getPOV(0) == 180){
			syValue -= 0.205;
		}
		else if (uStick.getPOV(0) == 0){
			syValue += 0.025;
		}
		
		counterCheck();
		
		servoX.set(sxValue);
		servoY.set(syValue);
		
	}
	
	
	public void cameraReset(){
		
			servoX.set(0);
			servoY.set(0);
		
	}
	public void counterCheck(){
		if(sxValue < 0){
			sxValue = 0;
			
		}
		if (syValue < 0){
			syValue = 0;
		}
		if (sxValue > 1 ){
			sxValue = 1;
		}
		if (syValue > 1){
			syValue = 1;
		}
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
		cameraReset();
	}

	@Override
	public void teleopPeriodic() {
		// TODO Auto-generated method stub
		
		if(uStick.getRawButton(2) == true){
			cameraReset();
		} else{
			cameraMovement();
		}
	}
	
	

}
