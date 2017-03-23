package org.usfirst.frc.team1989.robot;

public class RobotMap {
	
	// Talon Controllers for moving the robot
	public static final int FRONT_LEFT_DRIVE_TALON = 3;
	public static final int FRONT_RIGHT_DRIVE_TALON = 9;
	public static final int REAR_LEFT_DRIVE_TALON = 7;
	public static final int REAR_RIGHT_DRIVE_TALON = 5;
	
	// Talon Controllers for the Winch
	public static final int LEFT_WINCH_TALON = 4;
	public static final int RIGHT_WINCH_TALON = 2;
	
	// Talon Gear Motor
	public static final int GEAR_PUSHER_TALON = 6;
	
	// Ball (Fuel) Conveyor Talon Motor
	public static final int BALL_CONVEYOR_TALON = 1;
	
	// Ball (Fuel) Output Wheel Talon Motor
	public static final int BALL_OUTPUT_WHEEL_TALON = 10;
	
	// Servos
	public static final int SERVO_X = 0;
	public static final int SERVO_Y = 1;
	
	// Gear Limit Switch
	public static final int GEAR_LIMIT_SWITCH = 1;
	
	// Driving Joystick
	public static final int DRIVE_JOYSTICK = 0;
	
	// Utility Joystick
	public static final int UTILITY_JOYSTICK = 1;
	
	// Encoder Pulse Ratio
	public static final int ENCODER_PULSE_RATIO = 73;
	
	// Encoder Turn Ratio
	public static final int ENCODER_TURN_RATIO = ENCODER_PULSE_RATIO * 7;
	
	// Drive Max Voltage for Talons in Tele and Autonomous
	public static final double DRIVE_MAX_VOLTAGE_TELEOP = 12;
	public static final double DRIVE_MAX_VOLTAGE_AUTO = 12;
	
	// Voltage Ramp Rate for Drive Motors
	public static final double DRIVE_VOLTAGE_RAMP_RATE = 15;
	
	// Voltage Ramp Rate for Winch Motors
	public static final double WINCH_VOLTAGE_RAMP_RATE = 30;
	
	public static CANTalon1989 driveFrontLeft = new CANTalon1989(3);
	public static CANTalon1989 driveFrontRight = new CANTalon1989(9);
	public static CANTalon1989 driveBackLeft = new CANTalon1989(7);
	public static CANTalon1989 driveBackRight = new CANTalon1989(5);
	public static CANTalon1989 winchLeft = new CANTalon1989(4);
	public static CANTalon1989 winchRight = new CANTalon1989(2);
	public static CANTalon1989 gearPusher = new CANTalon1989(6);
	public static CANTalon1989 ballConveyor = new CANTalon1989(1);
	public static CANTalon1989 ballOutputWheel = new CANTalon1989(10);
	
	public static void init() {
		if (Robot.isReal()) {
			
			driveFrontLeft = new CANTalon1989(FRONT_LEFT_DRIVE_TALON);
			driveFrontRight = new CANTalon1989(FRONT_RIGHT_DRIVE_TALON);
			driveBackLeft = new CANTalon1989(REAR_LEFT_DRIVE_TALON);
			driveBackRight = new CANTalon1989(REAR_RIGHT_DRIVE_TALON);
			winchLeft = new CANTalon1989(LEFT_WINCH_TALON);
			winchRight = new CANTalon1989(RIGHT_WINCH_TALON);
			gearPusher = new CANTalon1989(GEAR_PUSHER_TALON);
			ballConveyor = new CANTalon1989(BALL_CONVEYOR_TALON);
			ballOutputWheel = new CANTalon1989(BALL_OUTPUT_WHEEL_TALON);
			
		} else {
			
		}
	}
	
	
}
