package com.dstz.bpm.api.engine.plugin.context;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import java.util.List;

public interface BpmPluginContext<T extends BpmPluginDef> extends PluginContext, PluginParse<T>, Comparable<BpmPluginContext> {
  List<EventType> getEventTypes();
  
  Integer getSn();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\context\BpmPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */