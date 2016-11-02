package com.xy.xyblog.util;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class ParamUtil {
	/**
	 * 将请求的路径所带的参数 转换成bean对象<此方法会忽略bean对象外的参数不做处理>
	 * @param request
	 * @param beanClass
	 * @return
	 */
	public static <T> T toBean(HttpServletRequest request, Class<T> beanClass) throws UnsupportedEncodingException {
            request.setCharacterEncoding("utf-8");
		try {
			/** 创建封装数据的bean **/
			T bean = beanClass.newInstance();
			Map map = request.getParameterMap();
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
