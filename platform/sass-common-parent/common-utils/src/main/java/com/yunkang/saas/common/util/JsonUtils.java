package com.yunkang.saas.common.util;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class JsonUtils {

	public static String toJson(Object obj) {
		try {
			return getObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T toObject(String jsonStr, Class<T> valueType) {
		if (jsonStr == null) {
			return null;
		}
		try {
			if (STR_CLASSPATH.equals(valueType.getName())) {
				return (T) jsonStr;
			} else {
				return getObjectMapper().readValue(jsonStr, valueType);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> List<T> toList(String jsonStr, Class<T> valueType) {
		if (jsonStr == null) {
			return null;
		}
		try {
			List<T> result = getObjectMapper().readValue(jsonStr, new TypeReference<T>() {
			});
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final String STR_CLASSPATH = "java.lang.String";
	private static ObjectMapper objectMapper;

	private static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			synchronized (JsonUtils.class) {
				if (objectMapper == null) {
					objectMapper = new ObjectMapper();
					objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
					SimpleModule simpleModule = new SimpleModule();
					simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
					simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
					simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
					objectMapper.registerModule(simpleModule);
				}
			}
		}
		return objectMapper;
	}
}
