package com.dstz.bpm.engine.util;

import com.dstz.base.core.util.AppUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import java.lang.reflect.Method;

public class HandlerUtil {
	public static int g(String handler) {
		if (handler.indexOf(".") == -1)
			return -1;
		String[] aryHandler = handler.split("[.]");
		if (aryHandler.length != 2)
			return -1;
		String beanId = aryHandler[0];
		String method = aryHandler[1];
		Object serviceBean = null;
		try {
			serviceBean = AppUtil.getBean(beanId);
		} catch (Exception ex) {
			return -2;
		}
		if (serviceBean == null)
			return -2;

		try {
			Method invokeMethod = serviceBean.getClass().getDeclaredMethod(method, new Class[] { ActionCmd.class });

			return 0;
		} catch (NoSuchMethodException e) {
			return -3;
		} catch (Exception e) {
			return -4;
		}
	}

	public static void a(ActionCmd actionModel, String handler) throws Exception {
		if (StringUtil.isEmpty(handler))
			return;
		String[] aryHandler = handler.split("[.]");
		if (aryHandler == null || aryHandler.length != 2)
			return;
		String beanId = aryHandler[0];
		String method = aryHandler[1];

		Object serviceBean = AppUtil.getBean(beanId);

		if (serviceBean == null)
			return;
		Method invokeMethod = serviceBean.getClass().getDeclaredMethod(method, new Class[] { ActionCmd.class });
		invokeMethod.invoke(serviceBean, new Object[] { actionModel });
	}
}