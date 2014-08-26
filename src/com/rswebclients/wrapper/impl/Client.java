package com.rswebclients.wrapper.impl;

import java.applet.Applet;

import com.rswebclients.RSW;
import com.rswebclients.util.ReflectionUtils;
import com.rswebclients.wrapper.Wrapper;

public class Client extends Wrapper {
	
	private Stream stream;
	private Player myPlayer;
	
	public Client(Class<?> clazz, final Object instance) {
		super(clazz, instance);
	}
	
	public Applet getApplet() {
		return (Applet)instance;
	}
	
	public Stream getStream() {
		return stream;
	}
	
	public Player getMyPlayer() {
		if(myPlayer == null) {
			myPlayer = new Player(ReflectionUtils.getClass(RSW.getLoader(), "Player"),
					ReflectionUtils.getFieldValue(wrappedClass, "myPlayer", instance));
		}
		return myPlayer;
	}
	
	public void start() {
		getApplet().init();
		getApplet().start();
		
		this.stream = new Stream(ReflectionUtils.getClass(RSW.getLoader(), "Stream"),
				ReflectionUtils.getFieldValue(wrappedClass, "stream", instance));
	}
	
	public void close() {
		getApplet().stop();
		getApplet().destroy();
	}
}
