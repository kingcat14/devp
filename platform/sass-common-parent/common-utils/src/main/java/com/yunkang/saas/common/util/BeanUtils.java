package com.yunkang.saas.common.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

public class BeanUtils {

	public static <T> T transform(Class<T> toClass, Object from) {
		if (from == null) {
			return null;
		}
		T toInstance = null;
		try {
			toInstance = toClass.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		org.springframework.beans.BeanUtils.copyProperties(from, toInstance, getNullPropertyNames(from));
		return toInstance;
	}

	public static void copyNotNullProperties(Object source, Object target) {


		org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	private static String[] getNullPropertyNames(Object from) {
		final BeanWrapper src = new BeanWrapperImpl(from);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<String>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
}
