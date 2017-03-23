package org.usfirst.frc.team1989.robot.commands.autonomous;

import org.usfirst.frc.team1989.robot.commands.PlayAction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PlayRecording extends CommandGroup {
	public PlayRecording(String fileName) {
		addSequential(new PlayAction(fileName));
	}
}
