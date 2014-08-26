package com.rswebclients.wrapper.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.rswebclients.RSW;
import com.rswebclients.util.ReflectionUtils;
import com.rswebclients.wrapper.Wrapper;

public class ItemDef extends Wrapper{
	
	private static Class<?> itemDef;
	private final String name;
	private final String actions[];
	
	public static void init() {
		itemDef = ReflectionUtils.getClass(RSW.getLoader(), "ItemDef");
	}
	
	public ItemDef(Class<?> clazz, Object instance) {
		super(clazz, instance);
		this.name = ReflectionUtils.getFieldValue(clazz, "name", instance);
		this.actions = ReflectionUtils.getFieldValue(clazz, "actions", instance);
	}
	
	public static ItemDef forId(int id) {
		Method method = ReflectionUtils.getMethod(itemDef, "forID", int.class);
		return new ItemDef(itemDef, ReflectionUtils.invoke(null, method, id));
	}
	
	public String getName() {
		return name;
	}
	
	public List<String> getActions() {
		List<String> list = new ArrayList<>(actions.length);
		for(String action : actions)
			if(action != null)
				list.add(action.toLowerCase());
		return list;
	}
}
