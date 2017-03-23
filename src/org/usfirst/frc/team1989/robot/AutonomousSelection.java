package org.usfirst.frc.team1989.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1989.robot.commands.autonomous.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class AutonomousSelection {
	
	public NetworkTable dashboard;
	
	public AutonomousSelection() {

	}
	
	public void setupSelections() {
		if (dashboard == null) {
			dashboard = NetworkTable.getTable("SmartDashboard");
		}
		String[] choices = new String[] {"Left Gear Peg", 
										 "Center Gear Peg",
										 "Right Gear Peg",
										 "Cross the Baseline",
										 "Record Left Gear Peg",
										 "Record Center Gear Peg",
										 "Record Right Gear Peg",
										 "Play Left Gear Peg Recording",
										 "Play Center Gear Peg Recording",
										 "Play Right Gear Peg Recording"
										 };
		
		dashboard.putStringArray("Auto List",choices);
	}
	
	public CommandGroup getCommandGroupForSelection() {
		String selection = SmartDashboard.getString("Auto Selector", "Center Gear Peg").toLowerCase();
		if (selection.contains("record")) {
			if (selection.contains("right")) {
				return new StartRecording("RightGear");
			} else if (selection.contains("left")) {
				return new StartRecording("LeftGear");
			} else {
				return new StartRecording("CenterGear");
			}
			
		} else if (selection.contains("play")) {
			if (selection.contains("right")) {
				return new PlayRecording("RightGear");
			} else if (selection.contains("left")) {
				return new PlayRecording("LeftGear");
			} else {
				return new PlayRecording("CenterGear");
			}
		} else if (selection.contains("gear")) {
			if (selection.contains("left")) {
				return new AutoLeftGear();
			} else if (selection.contains("right")) {
				return new AutoRightGear();
			} else {
				return new AutoCenterGear();
			}
		} else if (selection.contains("baseline")) {
			return new AutoCrossBaseline();
		} else {
			return new AutoCenterGear();
		}
	}
}
