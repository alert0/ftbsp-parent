package com.dstz.bpm.engine.plugin.session;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bus.api.model.IBusinessData;
import java.util.Map;
import org.activiti.engine.delegate.VariableScope;

public interface BpmPluginSession extends Map<String, Object> {
  IBpmInstance getBpmInstance();
  
  Map<String, IBusinessData> getBoDatas();
  
  VariableScope getVariableScope();
  
  EventType getEventType();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\session\BpmPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */