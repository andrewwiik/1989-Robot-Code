package org.usfirst.frc.team1989.robot.commands.autonomous;

import org.usfirst.frc.team1989.robot.commands.AutoDrive;
import org.usfirst.frc.team1989.robot.commands.PushGear;
import org.usfirst.frc.team1989.robot.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCenterGear extends CommandGroup {
	public AutoCenterGear() {
		addSequential(new AutoDrive(64, 0.7, true, false, false));
		addSequential(new AutoDrive(10, 0.3, true, true, true));
		addSequential(new Wait(0.25));
		addSequential(new PushGear());
		addSequential(new Wait(0.25));
		addSequential(new AutoDrive(20, -0.3, true, true, true));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
		
	}

}
