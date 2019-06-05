package com.dstz.bpm.api.engine.action.cmd;

import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.model.task.IBpmTask;

public interface TaskActionCmd extends ActionCmd {
  ActionType getActionType();
  
  String getTaskId();
  
  String getNodeId();
  
  IBpmTask getBpmTask();
  
  void setBpmTask(IBpmTask paramIBpmTask);
  
  String getDestination();
  
  void setDestination(String paramString);
  
  String getOpinion();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\cmd\TaskActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */