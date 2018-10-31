package com.zjk.module.common.data.util;

import com.zjk.module.common.data.specification.SpecificationOperate;
import org.apache.commons.lang3.StringUtils;

public class SpecificationUtil {

	public static final String PERCENT = "%";

	public static String like(SpecificationOperate operate, String string) {
		String tmp;
		switch (operate) {
			case LIKE:
				tmp = new StringBuffer(PERCENT).append(StringUtils.trim(string)).append(PERCENT).toString();
				break;
			default:
				tmp = null;
		}
		return tmp;
	}

}
