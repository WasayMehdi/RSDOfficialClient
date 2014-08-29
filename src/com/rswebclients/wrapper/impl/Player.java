package com.rswebclients.wrapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.rswebclients.RSW;
import com.rswebclients.util.ReflectionUtils;
import com.rswebclients.wrapper.Wrapper;

public class Player extends Wrapper{
		
	public Player(Class<?> clazz, final Object instance) {
		super(clazz, instance);
	}
	
	public String getName() {
		return super.getFieldVal("name");
	}
	
	public int getX() {
        return super.getFieldVal("x");
	}
	
	public int getY() {
		return super.getFieldVal("y");
	}
	
}
