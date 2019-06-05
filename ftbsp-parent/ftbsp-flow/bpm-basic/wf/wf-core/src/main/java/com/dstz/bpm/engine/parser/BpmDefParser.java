package com.dstz.bpm.engine.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;

interface BpmDefParser<D extends BpmDef> {
	Object parseDef(D paramD, String paramString);

	void parse(D paramD, JSONObject paramJSONObject);

	String getKey();

	Class getClazz();

	boolean isArray();

	String validate(Object paramObject);

	void setDefParam(D paramD, Object paramObject);

	void JSONAmend(D paramD, Object paramObject, JSON paramJSON);
}