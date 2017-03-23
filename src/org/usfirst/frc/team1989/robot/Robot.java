package org.usfirst.frc.team1989.robot;

//import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
//import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.interfaces.Accelerometer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot implements cmd{

	// Define class attributes
	Integer tmpint;
	Integer autoStatus;
	int autoMode;
	Timer t1;
	Integer encoderPulseRatio = 1;// needs to be determined, possibly at various speeds, will be in inches, default at 0.6 speed, ratio of distance to gear pulses
	Integer averageEncPos = 1;//calculate
	Double checkAmpValue;
	public static MecDriveCmd mDrive = new MecDriveCmd(Components.driveFrontLeft, Components.driveBackLeft, Components.driveFrontRight, Components.driveBackRight, Components.driveStick);
	CameraControl camControl = new CameraControl(Components.servoX, Components.servoY, Components.uStick);
	public static GearPushCmd gearPusher = new GearPushCmd(Components.gearMotor, Components.driveStick);
	ClimberCmd climber = new ClimberCmd(Components.climberLeft, Components.climberRight, Components.driveStick);
	BallSystemCmd ballSystem = new BallSystemCmd(Components.ballConveyor, Components.ballOutputWheel, Components.driveStick);
	int testState;
	int temp;
	
	public static String autoFileDirecctory = new String("/home/admin/autoJSON/");
	
	public CommandGroup autonomousCommand;
	public static AutonomousSelection autonomousSelection;
	@Override
	public void robotInit() {
		// Implement the functions for the components
	
		//EncoderDistanceCMD encDisTest = new EncoderDistanceCMD(Components.driveBackLeft, Components.driveBackRight, Components.driveStick);
		Components.driveFrontLeft.setVoltageRampRate(15);
		Components.driveFrontRight.setVoltageRampRate(15);
		Components.driveBackLeft.setVoltageRampRate(15);
		Components.driveBackRight.setVoltageRampRate(15);
		Components.climberLeft.setVoltageRampRate(30);
		Components.climberRight.setVoltageRampRate(30);
		
		Components.driveFrontLeft.maxI = 12;
		Components.driveFrontRight.maxI = 12;
		Components.driveBackLeft.maxI = 12;
		Components.driveBackRight.maxI = 12;
		
		// Add functions to the cmdlist
		SharedStuff.cmdlist.add(mDrive);
		SharedStuff.cmdlist.add(camControl);
		SharedStuff.cmdlist.add(gearPusher);
		SharedStuff.cmdlist.add(climber);
		SharedStuff.cmdlist.add(ballSystem);
		
		// Activate the camera 
		camControl.cameraReset();
		CameraServer.getInstance().startAutomaticCapture();
		
		Components.climberLeft.setVoltageRampRate(30);
		Components.climberRight.setVoltageRampRate(30);
		
		autonomousSelection = new AutonomousSelection();
		//t1.start();
	}

	
	@Override
	public void autonomousInit() {
		mDrive.resetEncoders();
		mDrive.resetEncoders();
		
		autonomousCommand = autonomousSelection.getCommandGroupForSelection();
		
		// make sure the returned value is not null
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
//		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
//			SharedStuff.cmdlist.get(i).autonomousInit();
//		}
//		
//		autoStatus = 0;
//		autoMode = (SmartDashboard.getBoolean("DB/Button 0") ? 1 : 0)
//				+ (SmartDashboard.getBoolean("DB/Button 1") ? 2 : 0)
//				+ (SmartDashboard.getBoolean("DB/Button 2") ? 4 : 0)
//				+ (SmartDashboard.getBoolean("DB/Button 2") ? 8 : 0);
//		SharedStuff.msg[0] = "Automode " + autoMode;
//		Components.driveBackLeft.setEncPosition(0);
//		Components.driveBackRight.setEncPosition(0);
//		gearPusher.pushRoutine = false;
//		t1 = new Timer();
//		t1.stop();
//		t1.reset();
//		testState = 0;
//		temp = 0;
//		Components.driveFrontLeft.setVoltageRampRate(15);
//		Components.driveFrontRight.setVoltageRampRate(15);
//		Components.driveBackLeft.setVoltageRampRate(15);
//		Components.driveBackRight.setVoltageRampRate(15);
	
	}
		
	/**
	 * This function is called periodically during autonomous
	 ?//*/
	@Override
	
	
	
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
//		if (autoMode == 1){
//			driveFoward();
//		}
//		if (autoMode == 2){
//			driveFowardGear();
//		}
//		if(autoMode == 3){
//			turnGear(0.5); //gear when starting on the left
//		} 
//		if (autoMode == 4){
//			turnGear(0.5); // gear when starting on the right
//		}
//		
//		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
//			SharedStuff.cmdlist.get(i).autonomousPeriodic();
//		}
//		Integer temp = Components.driveBackLeft.getEncPosition();
//		Components.writemessage.setmessage(1, autoStatus.toString());
//		Components.writemessage.setmessage(2, Components.driveStick.pTwist.toString());
//		Components.writemessage.setmessage(3, temp.toString());
//
//		
//		
//			
//			
//		Components.writemessage.updatedash();
		
		
		/*if(testState == 0){
			t1.stop();
			t1.reset();
			t1.start();
			testState = 1;
		}
		if(testState == 1){
			Components.driveStick.pY = 1.0;
			if(t1.get() > 2){
				Components.driveStick.pY = 0.0;
				t1.stop();
			}
		}*/
		
		
		
		
		
	
	}
	public void driveFoward(){
		Components.writemessage.setmessage(7, autoStatus.toString());
		
		if(autoStatus == 0){
			t1.start();
			
			autoStatus = 1;
		} else if(autoStatus == 1){
			Components.driveStick.pY = 0.3;
			if (t1.get() > 4.5){
				autoStatus = 2;
			}
		} else if (autoStatus == 2){
			Components.driveStick.pY = 0.0;
			Components.driveStick.pTwist = 0.0;
			if (mDrive.offset != 0){
				mDrive.offset = 0;
			}
		}
	}
	
	
	public void driveFowardGear(){
		if(autoStatus == 0){
			mDrive.driveFoward(144, 0.25, true);
			if(mDrive.driveFlag == false){
				t1.start();
				autoStatus = 1;	
				Components.driveBackLeft.setEncPosition(0);
				Components.driveBackRight.setEncPosition(0);
			}
		}
		else if(autoStatus == 1){
			if(t1.get() > 0.25){
				autoStatus = 2;
				t1.stop();
				t1.reset();
			}
		}
		
		else if(autoStatus == 2){
			gearPusher.gearPushStart();
			autoStatus = 3;
		} else if (autoStatus == 3){
			if(gearPusher.pushRoutine == true ){
				gearPusher.gearPush();
				if(gearPusher.pushRoutine == false){
					autoStatus = 4;
				}
				
			}
		}
		else if(autoStatus == 4){
					autoStatus = 5;
					t1.start();
					Components.driveBackLeft.setEncPosition(0);
					Components.driveBackRight.setEncPosition(0);
				
		} 
		else if (autoStatus == 5){
			if (t1.get() < 2){
				Components.driveStick.pY = -0.3;
				
			}
			else{
			autoStatus = 6;
			Components.driveStick.pY = 0.0;
			Components.driveBackLeft.setEncPosition(0);
			Components.driveBackRight.setEncPosition(0);
			}
		}
 		else{
			Components.driveStick.pY = 0.0;
			Components.driveStick.pX = 0.0;
			Components.driveStick.pTwist = 0.0;
			
		}
	}
	
	public void turnGear(double turnSpeed){ // true for right false for left may need to be double checked
		int fowardDistance = 85;
		
		if(autoStatus == 0){
			Components.driveStick.pY = 0.35;
			Components.driveBackLeft.setPosition(0);
			Components.driveBackRight.setEncPosition(0);
			
			t1.stop();
			t1.reset();
			autoStatus = 1;
			
		} else if (autoStatus == 1){
			if(Components.driveBackLeft.getEncPosition() > fowardDistance * mDrive.encPulseRatio ){
			System.out.println("2");
				
				autoStatus = 2;
				Components.driveStick.pY = 0.0;
				Components.driveBackLeft.setEncPosition(0);
				Components.driveBackRight.setEncPosition(0);
				t1.start();
				
			}
		} else if (autoStatus == 2){
			if(t1.get() > 0.5){
				autoStatus = 3;
				Components.driveStick.pTwist = 0.5;
				Components.driveBackLeft.setEncPosition(0);
				Components.driveBackRight.setEncPosition(0);
			}
			Components.driveBackLeft.setEncPosition(0);
			Components.driveBackRight.setEncPosition(0);

		} else if(autoStatus == 3){
	
			System.out.println("Enc Pos: " + Components.driveBackLeft.getEncPosition());
				if (Math.abs(Components.driveBackRight.getEncPosition()) > mDrive.turnRatio ){
					Components.driveStick.pTwist = 0.0;
					autoStatus = 4;
					t1.start();
			} 
		} else if (autoStatus == 4){
			if(t1.get() > 0.5){
				autoStatus = 5; 
				t1.stop();
				t1.reset();
				
			}
		} else if(autoStatus == 5){
			Components.driveStick.pY = 0.4;
			if(Components.gearLimit.get() == true){
				Components.driveStick.pY = 0.0;
				autoStatus = 6;	
				Components.driveBackLeft.setEncPosition(0);
				Components.driveBackRight.setEncPosition(0);
			}
			
		}
		else if(autoStatus == 6){
			gearPusher.gearPushStart();
			autoStatus = 7;
		} else if (autoStatus == 7){
			if(gearPusher.pushRoutine == true ){
				gearPusher.gearPush();
				if(gearPusher.pushRoutine == false){
					autoStatus = 8;
				}
				
			}
		}
		else if(autoStatus == 8){
					autoStatus = 9;
					t1.stop();
					t1.reset();
					t1.start();
					Components.driveBackLeft.setEncPosition(0);
					Components.driveBackRight.setEncPosition(0);
				
		} 
		else if (autoStatus == 9){
			if (t1.get() < 2){
				Components.driveStick.pY = -0.3;
				autoStatus = 10;
			}
			else{
			autoStatus = 9;
			Components.driveStick.pY = 0.0;
			Components.driveBackLeft.setEncPosition(0);
			Components.driveBackRight.setEncPosition(0);
			}
		}
 		else{
			Components.driveStick.pY = 0.0;
			Components.driveStick.pX = 0.0;
			Components.driveStick.pTwist = 0.0;
			
		}
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopInit(){
		Components.driveBackLeft.setEncPosition(0);
		Components.driveBackRight.setEncPosition(0);
		Components.driveFrontLeft.setVoltageRampRate(30);
		Components.driveFrontRight.setVoltageRampRate(30);
		Components.driveBackLeft.setVoltageRampRate(30);
		Components.driveBackRight.setVoltageRampRate(30);
		Components.climberLeft.setVoltageRampRate(30);
		Components.climberRight.setVoltageRampRate(30);

	}
	@Override
	public void teleopPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).teleopPeriodic();
		}
		
		
		
		if(Components.gearLimit.get() == true){
			
			Components.writemessage.setmessage(3,"Ready to Deploy");
		}
		else{
			Components.writemessage.setmessage(3, "Do not Deploy");
		}
		
		Components.writemessage.updatedash();
		
		
	}
	
	@Override
	public void disabledInit() {
	//	addSequential(new AutoDrive(double distanceInInches, double pY));
		if (autonomousSelection != null) {
			autonomousSelection.setupSelections();
		}
	}

	@Override
	public void disabledPeriodic() {
//		Scheduler.getInstance().run();
	}

	public void testInit(){
		// Values for testing the number of times a second that we run our periodic
		tmpint = new Integer(0);
		t1 = new Timer();
		if (autonomousSelection != null) {
			autonomousSelection.setupSelections();
		}
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		for (int i = 0; i < SharedStuff.cmdlist.size(); i++) {
			SharedStuff.cmdlist.get(i).autonomousPeriodic();
		}
		
		

		
		
		
	}
}
	
