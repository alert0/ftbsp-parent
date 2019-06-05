package com.dstz.activiti.rest.editor.main;

import com.alibaba.fastjson.JSONObject;
import java.io.InputStream;
import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StencilsetRestResource {
	@RequestMapping(value = { "/editor/stencilset" }, method = { RequestMethod.GET })
	@ResponseBody
	public JSONObject getStencilset() {
		InputStream stencilsetStream = getClass().getClassLoader().getResourceAsStream("stencilset.json");
		try {
			return JSONObject.parseObject(IOUtils.toString(stencilsetStream));
		} catch (Exception e) {
			throw new ActivitiException("Error while loading stencil set", e);
		}
	}
}