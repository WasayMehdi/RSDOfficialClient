package com.rswebclients.wrapper.impl;

import java.lang.reflect.Method;

import com.rswebclients.util.ReflectionUtils;
import com.rswebclients.wrapper.Wrapper;

public class Stream extends Wrapper {
	private Method createFrame;
	private Method writeWord;
	public Stream(final Class<?> clazz, final Object stream) {
		super(clazz, stream);
		this.createFrame = ReflectionUtils.getMethod(clazz, "createFrame", int.class);
		this.writeWord = ReflectionUtils.getMethod(clazz, "writeWord", int.class);
	}
		
	public Class<?> getRefClass() {
		return wrappedClass;
	}
	
	public void createFrame(int i) {
		ReflectionUtils.invoke(instance, createFrame, i);
	}
	
	public void writeWord(int i) {
		ReflectionUtils.invoke(instance, writeWord, i);
	}
}
