package org.usfirst.frc.team1989.robot.commands.autonomous;

import org.usfirst.frc.team1989.robot.commands.AutoDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCrossBaseline extends CommandGroup {
	public AutoCrossBaseline() {
		addSequential(new AutoDrive(64, 0.7, false, false, false));
		addSequential(new AutoDrive(10, 0.3, false, true, true));
	}
}
