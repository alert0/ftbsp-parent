package com.dstz.bpm.api.engine.plugin.context;

import com.alibaba.fastjson.JSON;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;

public interface PluginParse<T extends BpmPluginDef> {
  T parse(JSON paramJSON);
  
  T parse(String paramString);
  
  JSON getJson();
  
  String getType();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\context\PluginParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */