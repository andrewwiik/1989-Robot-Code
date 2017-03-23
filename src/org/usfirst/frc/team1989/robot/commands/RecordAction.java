package org.usfirst.frc.team1989.robot.commands;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.usfirst.frc.team1989.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class RecordAction extends Command {

	private String fileName;
	
	private JSONArray data;
	private JSONObject lastObject;
	private Timer timer;
	private boolean isFinished;
    
	public RecordAction(String fileName) {
		this.fileName = fileName;
		this.data =  new JSONArray();
	}
	
	protected void initialize() {
		this.timer = new Timer();
		timer.start();
		this.data =  new JSONArray();
    }
    
    @SuppressWarnings("unchecked")
	protected void execute() {
    	
    	boolean hasChanged = false;
    	
    	Double t = timer.get();
    	Double sX = new Double(Robot.mDrive.driveStick.sgetX());
    	Double sY = new Double(Robot.mDrive.driveStick.sgetY());
    	Double sT = new Double(Robot.mDrive.driveStick.sgetTwist());
    	Boolean slower = new Boolean(Robot.mDrive.driveStick.getRawButton(11));
    	Integer rE = new Integer(Robot.mDrive.getRightEncoderValue());
    	Integer lE = new Integer(Robot.mDrive.getLeftEncoderValue());
    	
    	
    	if (this.lastObject != null) {
    		if (sX != this.lastObject.get("sX")) {
    			hasChanged = true;
    		} else if (sY != this.lastObject.get("sY")) {
    			hasChanged = true;
    		} else if (sT != this.lastObject.get("sT")) {
    			hasChanged = true;
    		} else if (slower != this.lastObject.get("slower")) {
    			hasChanged = true;
    		}
    	}
    	
    	if (hasChanged) {
    		JSONObject object = new JSONObject();
    		object.put("t", t);
        	object.put("sX", sX);
        	object.put("sY", sY);
        	object.put("sT", sT);
        	object.put("slower", slower);
        	object.put("rE", rE);
        	object.put("lE", lE);
        	this.data.add(object);
        	this.lastObject = object;
    	}
    	
    	if (Robot.mDrive.driveStick.getRawButton(11)) {
    		isFinished = true;
    	}
    	
    }
    
	@Override
	protected boolean isFinished() {
		if (isFinished) {
			String recordFilePath = new String(Robot.autoFileDirecctory + fileName + ".json");
			
			try {

				FileWriter file = new FileWriter(recordFilePath);
				file.write(data.toJSONString());
				file.flush();
				file.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			timer.stop();
			return true;
		}
		return false;
	}

}
