package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public final class Components {
	
	static CANTalon1989 driveFrontLeft = new CANTalon1989(3);
	static CANTalon1989 driveFrontRight = new CANTalon1989(9);
	static CANTalon1989 driveBackLeft = new CANTalon1989(7);
	static CANTalon1989 driveBackRight = new CANTalon1989(5);
	static CANTalon1989 climberLeft = new CANTalon1989(4);
	static CANTalon1989 climberRight = new CANTalon1989(2);
	public static CANTalon1989 gearMotor = new CANTalon1989(6);
	static CANTalon1989 ballConveyor = new CANTalon1989(1);
	static CANTalon1989 ballOutputWheel = new CANTalon1989(10);
	
	// Instantiating Servo
	static Servo servoX = new Servo(0);
	static Servo servoY = new Servo(1);
	
	public static DigitalInput gearLimit = new DigitalInput(1);

	// Instantiating Joysticks
	static JsScaled driveStick = new JsScaled(0);
	static JsScaled uStick = new JsScaled(1);//The uStick will stand for the utility joystick responsible for shooting and arm movement
	
	// Instantiate Gyro (Doesn't work)
	static Gyro gyro;
	
	
	
	static writemessage writemessage = new writemessage();

}
