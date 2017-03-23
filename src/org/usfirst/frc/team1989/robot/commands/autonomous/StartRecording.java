package org.usfirst.frc.team1989.robot.commands.autonomous;

import org.usfirst.frc.team1989.robot.commands.RecordAction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRecording extends CommandGroup {
	public StartRecording(String fileName) {
		addSequential(new RecordAction(fileName));
	}
}
