package com.dstz.bpm.engine.model;

import cn.hutool.core.date.DateUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.model.def.BpmVariableDef;

public class DefaultBpmVariableDef implements BpmVariableDef {
	private String X = "";

	private String name = "";

	private String key = "";

	private String br = "string";

	private Object bs = "";

	private boolean isRequired = false;

	private String description = "";

	public static Object getValue(String dataType, String value) {
		if ("double".equals(dataType))
			return new Double(value);
		if ("float".equals(dataType))
			return new Float(value);
		if ("int".equals(dataType)) {
			if (value == null || StringUtil.isEmpty(value)) {
				return Integer.valueOf(0);
			}
			return new Integer(value);
		}
		if ("date".equals(dataType)) {
			return DateUtil.parse(value);
		}

		return value;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String varKey) {
		this.key = varKey;
	}

	public String getDataType() {
		return this.br;
	}

	public void setDataType(String dataType) {
		this.br = dataType;
	}

	public Object getDefaultVal() {
		return this.bs;
	}

	public void setDefaultVal(Object defaultVal) {
		this.bs = defaultVal;
	}

	public void setDefaultVal(String defaulVal2) {
		this.bs = getValue(this.br, defaulVal2);
	}

	public boolean getIsRequired() {
		return this.isRequired;
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean isRequired() {
		return this.isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public String getDescription() {
		return (this.description == null) ? "" : this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNodeId() {
		return this.X;
	}

	public void setNodeId(String nodeId) {
		this.X = nodeId;
	}
}