package com.serverless.framework.factories;

import java.util.HashMap;
import java.util.Map;

public class BeansFactory {
	private static Map<String, Object> beans = new HashMap<>();

	public synchronized void put(String key, Object bean) {
		if(key == null || bean == null) {
			return;
		}
		beans.put(key, bean);
	}

	public <T> T get(String key) {
		if(beans.containsKey(key)) {
			return (T) beans.get(key);
		}

		return null;
	}

}
