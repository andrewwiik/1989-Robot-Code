package org.usfirst.frc.team1989.robot;


public class ClimberCmd implements cmd{

	// Attributes: 2 motors, joystick, encoder position, and a toggle for the process.
	CANTalon1989 climberLeft; //encoder
	CANTalon1989 climberRight;
	JsScaled driveStick;
	
	// Constructor with 2 motors - needed after last motor was placed onto robot	
	public ClimberCmd(CANTalon1989 climberLeft, CANTalon1989 climberRight, JsScaled driveStick){
		this.climberLeft = climberLeft;
		this.climberRight = climberRight;
		this.driveStick = driveStick;
	}
	
	// Constructor with 1 motor - initial design only had 1, also a fail safe if one motor breaks down.
	public ClimberCmd(CANTalon1989 climberLeft, JsScaled driveStick){
		this.climberLeft = climberLeft;
		this.driveStick = driveStick;
	}
	
	// Move robot up the rope
	public void climberLift(){
		climberLeft.set(-1);
		climberRight.set(-1);
		//SmartDashboard.putString("DB/String 0", Components.climberLeft.getEncPosition());
		
	}
	
	// Drop the robot down the rope
	public void climberDrop(){
		climberLeft.set(1);
		climberRight.set(1);	
	}
	
	// Stop climber motors
	public void climberStop(){
		climberLeft.set(0);
		climberRight.set(0);
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
	}

	@Override
	public void teleopPeriodic() {
		// If we aren't in the preset routine, allow user activated controls
		if(driveStick.getRawButton(4) == true){
			climberLift();
		} else if (driveStick.getRawButton(6) == true){
			//climberDrop();
		}
		else{
			climberStop();
		}
	}
}
