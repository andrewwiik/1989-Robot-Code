package org.usfirst.frc.team1989.robot.commands;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.usfirst.frc.team1989.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PlayAction extends Command {

	private String fileName;
	private JSONArray data;
	private Timer timer;
	private int currentIndexPosition;
	private double sX;
	private double sY;
	private double sT;
	private boolean slower;
//	Iterator<JSONObject> iterator;
	
	public PlayAction(String fileName) {
		this.fileName = fileName;
	}
	protected void initialize() {
		this.timer = new Timer();
		timer.start();
		
		String filePath = new String(Robot.autoFileDirecctory + this.fileName + ".json");
		JSONParser parser = new JSONParser();
		Reader reader;
		
		try {
			reader = new FileReader(filePath);
			try {
				data = (JSONArray) parser.parse(reader);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		this.currentIndexPosition = 0;
	}
	
	protected void execute() {
		if (this.data != null) {
			JSONObject object = (JSONObject) data.get(currentIndexPosition);
			double currentTime = timer.get();
			double t = ((Double)object.get("t")).doubleValue();
			if (t > currentTime - 0.005 && t < currentTime + 0.005) {
				currentIndexPosition++;
				
				sX = ((Double)object.get("sX")).doubleValue();
				sY = ((Double)object.get("sY")).doubleValue();
				sT = ((Double)object.get("sT")).doubleValue();
				slower = ((Boolean)object.get("slower")).booleanValue();
			}
			
			if (slower) {
				Robot.mDrive.driveTrain.mecanumDrive_Cartesian(sX/2, sY/2, sT/2, 0);
			} else {
				Robot.mDrive.driveTrain.mecanumDrive_Cartesian(sX, sY, sT, 0);
			}
		}
	}
	@Override
	protected boolean isFinished() {
		if (this.data != null) {
			if (this.currentIndexPosition > data.size()) {
				timer.stop();
				return true;
			} else {
				return false;
			}
		} else {
			timer.stop();
			return true;
		}
	}

}
