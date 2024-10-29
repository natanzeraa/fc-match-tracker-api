package com.scoreboard.scoreboard.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class MergeableDataHandler {
	public void mergeData(Map<String, Object> parcialData, Object classData) {
		Class<?> targetClass = classData.getClass();

		ObjectMapper mapper = new ObjectMapper();

		Object convertedPlayerMatch = mapper.convertValue(parcialData, targetClass);

		parcialData.forEach((propertyName, propertyValue) -> {
			Field field = ReflectionUtils.findField(targetClass, propertyName);

			if (field != null) {
				field.setAccessible(true);
				Object convertedFinalValue = ReflectionUtils.getField(field, convertedPlayerMatch);
				ReflectionUtils.setField(field, classData, convertedFinalValue);
			}
		});
	}
}
