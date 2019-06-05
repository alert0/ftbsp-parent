package com.dstz.bpm.api.engine.plugin.context;

import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import java.io.Serializable;

public interface PluginContext extends Serializable {
  public static final String PLUGINCONTEXT = "PluginContext";
  
  Class<? extends RunTimePlugin> getPluginClass();
  
  BpmPluginDef getBpmPluginDef();
  
  String getTitle();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\context\PluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */