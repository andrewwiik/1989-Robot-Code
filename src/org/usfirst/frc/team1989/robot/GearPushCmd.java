package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.Timer;

public class GearPushCmd implements cmd {

	//Class Attributes
	CANTalon1989 gearPush;
	JsScaled driveStick;
	JsScaled utilityStick;
	Timer gearTimer = new Timer();
	int state = 0;
	public Boolean pushRoutine = false;
	
	//Constructor
	public GearPushCmd(CANTalon1989 gearPush, JsScaled driveStick, JsScaled utilityStick){
		this.gearPush = gearPush;
		this.driveStick = driveStick;
		this.utilityStick = utilityStick;
	}
	
	public GearPushCmd(CANTalon1989 gearPush, JsScaled driveStick){
		this.driveStick = driveStick;
		this.gearPush = gearPush;
	}
	
	//Class Method
	public void gearPushStart(){
		pushRoutine = true;
	}
	
	// Push the gear based on 
	public void gearPush(){
		if (state == 0){
			state = 1;
			gearTimer.reset();
			gearTimer.stop();
			gearTimer.start();
		}	
		else if (state == 1){
			pushFoward();
			//gearPush.set(-1);
			if (gearTimer.get() > 1.5){
				state = 2;
				//pushStop();
				gearTimer.reset();
				gearTimer.stop();
				gearTimer.start();
			}
		}
		else if (state == 2){
			pushBack();
			//gearPush.set(1);
			if (gearTimer.get() > 1.5){
				state = 3;
				gearTimer.reset();
				gearTimer.stop();
			
			}
		}
		else if (state == 3){
			state = 0;
			pushStop();
			pushRoutine = false;
		}		
	}
	
	
	public void pushFoward(){
		gearPush.set(.7);
		//SmartDashboard.putString("DB/String 5", " " + gearPush.get());
	}
	public void pushBack(){
		gearPush.set(-.7);
		//SmartDashboard.putString("DB/String 6", " " + gearPush.get());
	}
	public void pushStop(){
		gearPush.set(0);
	}
	
	
	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		pushRoutine = false;
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
		 
	}

	@Override
	public void teleopPeriodic() {
		// If depress trigger and not running, start the routine.
		if(driveStick.getRawButton(12) == true && pushRoutine == false){
			gearPushStart();
		}
		
		// If the routine has started allow it to continue
		if(pushRoutine == true){
			gearPush();
		}
		
		if(pushRoutine == false){
			if(driveStick.getRawButton(10) == true){
				pushBack();
			} else if(driveStick.getRawButton(9) == true) {
				pushFoward();
			} else {
				pushStop();
			}
		}
		
	}

}
