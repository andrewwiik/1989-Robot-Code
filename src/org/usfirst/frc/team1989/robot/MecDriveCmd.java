
package org.usfirst.frc.team1989.robot;
import edu.wpi.first.wpilibj.*;

public class MecDriveCmd implements cmd {
	
	// Instantiate Basic Components
	CANTalon1989 driveFrontLeft;
	CANTalon1989 driveFrontRight;
	CANTalon1989 driveBackLeft;//encoder motor
	CANTalon1989 driveBackRight;// encoder motor
	public RobotDrive driveTrain;
	public JsScaled driveStick;
	int encPulseRatio = 73;
	int breakDistance = 756;
	int remainingDistance;
	double currentPower;
	double minPower = 0.2;
	double speedRamp = 0.05;
	public double offset = -0.01;
	int resetTemp = 0;
	int turnRatio = encPulseRatio * 7;
	boolean turnFlag = false;
	boolean driveFlag = false;
	Timer resetTimer = new Timer();
	
	// Part of the Encoder Test (needs class scope, or it would go in test init)
	Integer encoderLeftCount = 0;
	Integer encoderRightCount= 0;
	Integer driveStateTemp = 0;
	Integer cycleDelay = 0;
	Integer deltaEncValue;
	Double driveTempAdjustment;
	Double driveTwistAdjustment = 0.0;
	int temp = 0;
	
	
	public MecDriveCmd(CANTalon1989 driveFrontLeft, CANTalon1989 driveBackLeft, CANTalon1989 driveFrontRight, CANTalon1989 driveBackRight, JsScaled driveStick){
		this.driveFrontLeft = driveFrontLeft;
		this.driveBackLeft = driveBackLeft;
		this.driveFrontRight = driveFrontRight;
		this.driveBackRight = driveBackRight;
		driveTrain = new RobotDrive(this.driveFrontLeft, this.driveBackLeft, this.driveFrontRight, this.driveBackRight);
		this.driveStick = driveStick;
		this.driveFrontRight.setInverted(true);
		this.driveBackRight.setInverted(true);
		
		this.driveBackLeft.setDistancePerPulse(encPulseRatio);
		this.driveBackRight.setDistancePerPulse(encPulseRatio);
	}
	
	// Check the values of each encorder and display it to the smart dashboard
	// This is part of the test code for the encoder based drive
	public void encoderCheck(){
		// If going straight get encoder values.  Otherwise set them to 0.
		if(Math.abs(driveStick.sgetTwist()) < 0.1 && Math.abs(driveStick.sgetX()) < 0.1 && Math.abs(driveStick.sgetY()) > 0.1){
			encoderLeftCount = driveBackLeft.getEncPosition();
			encoderRightCount = driveBackRight.getEncPosition();
		} 
		if(Math.abs(driveStick.sgetTwist()) > 0.1 || Math.abs(driveStick.sgetX()) > 0.1){
			driveBackLeft.setEncPosition(0);
			driveBackRight.setEncPosition(0);
		}
		// Output data to the dash
		Components.writemessage.setmessage(0, encoderLeftCount.toString());
		Components.writemessage.setmessage(1, encoderRightCount.toString());
	}
	
	public void resetEncoders() {
		driveBackLeft.setEncPosition(0);
		driveBackRight.setEncPosition(0);
	}
	
	// Convert distance to encoder values
	public void driveFoward(int inches, double startPower, boolean limitCheck){//assumes encoder reset to 0, must be driven backwards
		// Pulses to inches
		int pulseCount = inches * encPulseRatio;
		
		// Check for gear limit switch
		if (limitCheck == true){
			// Check based on encoders and gear limit switch not actuated
			if (Math.abs(driveBackLeft.getEncPosition()) <= pulseCount && Components.gearLimit.get() == false){	
				// Begin ramp down when apx. 6 inches from the target	
				if( Math.abs(Components.driveBackLeft.getEncPosition()) > pulseCount - breakDistance){
					if (startPower < 0){
						driveStick.pY = -0.3;
					}
					else{
						driveStick.pY = 0.3;
					}
				}else{
					driveStick.pY = startPower;
				}
				
				driveStick.pTwist = 0.0;
				// Used for autonomous - only true while driving
				driveFlag = true;
			} 
			else {
				// When either done driving or hit the peg stop the robot
				driveStick.pY = 0.0;
				driveStick.pTwist = offset;
				driveFlag = false;
				
			}
		}else {
			// Check based on encoders and gear limit switch not actuated
						if (Math.abs(driveBackLeft.getEncPosition()) <= pulseCount){	
							// Begin ramp down when apx. 6 inches from the target	
							if(pulseCount - breakDistance > Math.abs(Components.driveBackLeft.getEncPosition())){
								if (startPower < 0){
									driveStick.pY = -0.3;
								}
								else{
									driveStick.pY = 0.3;
								}
								
								
							}else{
								driveStick.pY = startPower;
							}
							
							driveStick.pTwist = 0.0;
							// Used for autonomous - only true while driving
							driveFlag = true;
						} 
						else {
							// When either done driving or hit the peg stop the robot
							driveStick.pY = 0.0;
							driveStick.pTwist = offset;
							driveFlag = false;
							
						}
				}
	}
	
	public void resetAttempt(boolean twistDirection){
		if(resetTemp == 0){
			driveFoward(10, - 0.3, false);
			if(driveFlag == false){
				resetTemp = 1;
				resetTimer.start();
			}
		} else if(resetTemp == 1){
			if (twistDirection == true){
				driveStick.pTwist = 0.2;
				if(resetTimer.get() > 0.25){
					driveStick.pTwist = 0.0;
					resetTimer.stop();
					resetTemp = 2;
				}				
			}
			else if(resetTemp == 2){
				driveFoward(15, 0.3, true);
			}
		}
		
		
		
	}
	
	public void turn(boolean directionCheck){// make 1 if direction is right and -1 if direction is left subject to change
		if(directionCheck == true ){
			if(Math.abs(driveBackRight.getEncPosition()) < turnRatio){
				driveStick.pTwist = -0.3;
				turnFlag = true;
			}
			else {
				driveStick.pTwist = 0.0;
				turnFlag = false;
				driveBackLeft.setEncPosition(0);
				driveBackLeft.setEncPosition(0);
			}
		}
		else{
			if(Math.abs(driveBackLeft.getEncPosition()) < turnRatio){
				driveStick.pTwist = 0.3;
				turnFlag = true;
			}
			else {
				driveStick.pTwist = 0.0;
				turnFlag = false;
				driveBackLeft.setEncPosition(0);
				driveBackLeft.setEncPosition(0);
			}
		}
	}
	
	
	
	
	
	// Make the robot drive straight
	/*public void encoderDrive(double jsX, double jsY, double jsTwist){
		if(jsX == 0 && jsTwist == 0 && jsY > 0){
			if(driveStateTemp == 0){
				encoderLeftCount = driveBackLeft.getEncPosition();
				encoderRightCount = driveBackRight.getEncPosition();
				deltaEncValue = encoderRightCount - encoderLeftCount;
					if(deltaEncValue != 0){
						driveStateTemp = 1;
						driveTempAdjustment = (double) (deltaEncValue/200);
					} else {
						driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist + driveTwistAdjustment, 0);
					}
			    } else if (driveStateTemp == 1){
				
				driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist + driveTempAdjustment + driveTwistAdjustment, 0);
				cycleDelay+=1;
				     if (cycleDelay == 2){
					      driveStateTemp = 2;
					      cycleDelay = 0;
				     }				
			    } 
			    else if(driveStateTemp == 2){
				 driveTwistAdjustment+= driveTempAdjustment/2;
				 driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist + driveTwistAdjustment, 0);
				 driveStateTemp = 0;
			     }
			} else{
				driveStateTemp = 0;
				driveTrain.mecanumDrive_Cartesian(jsX, jsY, jsTwist, 0);
				driveBackLeft.setEncPosition(0);
				driveBackRight.setEncPosition(0);
		}	
	}*/

	public void autonomousInit(){
		driveBackLeft.enableBrakeMode(true);
		driveBackRight.enableBrakeMode(true);
		driveFrontLeft.enableBrakeMode(true);
		driveFrontRight.enableBrakeMode(true);
		driveBackLeft.setEncPosition(0);
		driveBackRight.setEncPosition(0);
		resetTimer.stop();
		resetTimer.reset();
		
	}
	public void autonomousPeriodic() {
		
		//Components.writemessage.setmessage(5, driveStick.pY.toString());
		//Components.writemessage.updatedash();
		driveTrain.mecanumDrive_Cartesian(driveStick.pX, driveStick.pY, driveStick.pTwist + offset,0);//Last 0 is gyro angle needs to be checked if we get one
		
	}
	
	public int getLeftEncoderValue() {
		return driveBackLeft.getEncPosition();
	}
	
	public int getRightEncoderValue() {
		return driveBackRight.getEncPosition();
	}
	
	public void teleopInit(){}
	public void teleopPeriodic(){
		//encoderLeftCount = driveBackLeft.getEncPosition();
		//encoderRightCount = driveBackRight.getEncPosition();
		/*if(Components.gearLimit.get() == true){
			if(driveStick.sgetX() > 0){
				driveTrain.mecanumDrive_Cartesian(driveStick.sgetX(), 0, driveStick.sgetTwist(), 0);
			} else{
				driveTrain.mecanumDrive_Cartesian(driveStick.sgetX(), driveStick.sgetY(), driveStick.sgetTwist(), 0);
			}
		} else{
			driveTrain.mecanumDrive_Cartesian(driveStick.sgetX(), driveStick.sgetY(), driveStick.sgetTwist(), 0);
		}
		*/
		
		if(driveStick.getRawButton(11) == true){
			driveTrain.mecanumDrive_Cartesian(driveStick.sgetX()/2, driveStick.sgetY()/2, driveStick.sgetTwist()/2, 0);
		} else{
			driveTrain.mecanumDrive_Cartesian(driveStick.sgetX(), driveStick.sgetY(), driveStick.sgetTwist(), 0);
		}
		
		
		
		
		
	}
	
	public void toggleBreaks(boolean toggle) {
		driveFrontLeft.enableBrakeMode(toggle);
		driveFrontRight.enableBrakeMode(toggle);
		driveBackLeft.enableBrakeMode(toggle);
		driveBackRight.enableBrakeMode(toggle);
	}
	
	public void testInit(){}
	
	
	
	public void testPeriodic(){
		//driveTrain.mecanumDrive_Cartesian(driveStick.pX, driveStick.pY, driveStick.pTwist,0);//Last 0 is gyro angle needs to be checked if we get one
		// Display encoder values
		encoderCheck();
		driveTrain.mecanumDrive_Cartesian(driveStick.sgetX(), driveStick.sgetY(), driveStick.sgetTwist(), 0);
		Components.writemessage.updatedash();
	}
	

	    
}
