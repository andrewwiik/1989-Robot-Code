package org.usfirst.frc.team1989.robot;

import java.util.ArrayList;

public class SharedStuff{
	public static ArrayList<cmd> cmdlist = new ArrayList<cmd>();
	public static String[] msg = new String[10] ;
	public static String[] lastmsg = new String[10];
	public static Boolean[] led = new Boolean[5];
	public static Boolean[] lastled = new Boolean[5];
	
	

	/*
	    * returns an a_cmd with type stringcmd
	    * Not sure what the purpose of this is.  Will talk to Martin
	    */
	public static cmd findcmd(String cmd)
	{
		
		for (int i = 0; i < cmdlist.size(); i++) {
			 if (cmdlist.get(i).type == cmd){
				 return cmdlist.get(i);
			 };
		}

		return null;
	}
}