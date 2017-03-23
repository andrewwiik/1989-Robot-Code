package org.usfirst.frc.team1989.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A simple command that waits the specified amount of seconds before
 * declaring itself as finished.
 */
public class Wait extends Command {
	Timer t;
	double duration;

    public Wait(double duration) {
    	t = new Timer();
    	this.duration = duration;
    }

    protected void initialize() {
    	t.reset();
    	t.start();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return t.get() > duration;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}