package com.rswebclients.frame.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.rswebclients.RSW;
import com.rswebclients.util.ReflectionUtils;
import com.rswebclients.wrapper.impl.Client;
import com.rswebclients.wrapper.impl.Player;
import com.rswebclients.wrapper.impl.Stream;

public class CommandListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			Client client = RSW.getClient();
			/*String inputString = ReflectionUtils.getFieldValue(RSW.getClient().getRefClass(), "inputString", RSW.getClient().getApplet());
			  if(inputString == null)
			  		inputString = ReflectionUtils.getFieldValue(RSW.getClient().getRefClass(), "input", RSW.getClient().getApplet());
			  if(inputString == null)
				  return;
			if(inputString.startsWith("//")) {
				inputString = inputString.substring(2);
				//processCommand(inputString, client, client.getStream());
			}
			*/
		}
	}
	
	public static void processCommand(String command, Client client, Stream stream) {
		String s[] = command.split(" ");
		Player myPlayer = client.getMyPlayer();
		if(command.equalsIgnoreCase("test")) {
			System.out.println("test");
		}
	}

}
