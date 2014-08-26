package com.rswebclients;

import java.awt.Frame;
import java.util.HashMap;

import com.rswebclients.frame.MainFrame;
import com.rswebclients.frame.listeners.CommandListener;
import com.rswebclients.util.ReflectionUtils;
import com.rswebclients.wrapper.impl.Client;
import com.rswebclients.wrapper.impl.ItemDef;

public class RSW {
	private static RSWClassLoader loader;
	private static Class<?> clientClass;
	private static Client client;
	private static Class<?> rsw;
	private static HashMap<String, Object> data;
	
	public static Client getClient() {
		return client;
	}
	
	public static ClassLoader getLoader() {
		return loader;
	}
	
	public static void init(String s) {
		try {
			destroy();
			try {
				loader = RSWClassLoader.getRSWJar(s);
				rsw = loader.loadClass("RSWebclients");
				data = ReflectionUtils.getFieldValue(rsw, "data", null);
			} catch(Exception ex) {
				loader = RSWClassLoader.getExternalJar(s);
			}
			clientClass = loader.loadClass("client");
			client = new Client(clientClass, clientClass.newInstance());
			client.getApplet().addKeyListener(new CommandListener());
			Frame frame = MainFrame.getFrame();
			frame.add(client.getApplet());
			frame.setSize(MainFrame.getFrame().getPreferredSize());
			frame.pack();
			client.start();
			ItemDef.init();
			MainFrame.getFrame().setMessage("Currently playing: %s", s);
		}catch(Exception e) {
			e.printStackTrace();
			MainFrame.getFrame().setMessage("Failed to load: %s", s);
		}
	}
	public static boolean destroy() {
		try { 
			MainFrame.getFrame().remove(client.getApplet());
			client.close();
		} catch(Exception ex) {
			return false;
		}
		return true;
	}
}
