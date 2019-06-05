package com.dstz.bpm.api.engine.action.handler;

import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;

public interface ActionHandler<T extends ActionCmd> {
  void execute(T paramT);
  
  ActionType getActionType();
  
  int getSn();
  
  Boolean isSupport(BpmNodeDef paramBpmNodeDef);
  
  Boolean isDefault();
  
  String getConfigPage();
  
  String getDefaultGroovyScript();
  
  String getDefaultBeforeScript();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\handler\ActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */