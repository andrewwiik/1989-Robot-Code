package org.usfirst.frc.team1989.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1989.robot.commands.AutoDrive;
import org.usfirst.frc.team1989.robot.commands.PushGear;
import org.usfirst.frc.team1989.robot.commands.Wait;
import org.usfirst.frc.team1989.robot.commands.AutoTurn;

public class AutoRightGear extends CommandGroup {
	public AutoRightGear() {
		addSequential(new AutoDrive(74, 0.7, false, false, false));
		addSequential(new AutoDrive(6, 0.3, false, true, true));
		addSequential(new Wait(0.5));
		addSequential(new AutoTurn(-60.0,0.35, true));
		addSequential(new Wait(0.5));
		addSequential(new AutoDrive(38, 0.7, true, false, false));
		addSequential(new AutoDrive(10, 0.3, true, true, true));
		addSequential(new Wait(0.25));
		addSequential(new PushGear());
		addSequential(new Wait(0.25));
		addSequential(new AutoDrive(20, -0.3, true, true, true));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	}
}

