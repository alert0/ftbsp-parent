package com.dstz.bpm.api.model.task;

import java.util.Date;

public interface IBpmTask {
  public static final short STATUS_SUSPEND = 1;
  
  public static final short STATUS_NO_SUSPEND = 0;
  
  String getId();
  
  String getName();
  
  String getSubject();
  
  String getTaskId();
  
  String getActExecutionIdId();
  
  String getNodeId();
  
  String getInstId();
  
  String getDefId();
  
  String getAssigneeId();
  
  String getStatus();
  
  Integer getPriority();
  
  Date getCreateTime();
  
  Date getDueTime();
  
  String getTaskType();
  
  String getParentId();
  
  String getActInstId();
  
  void setAssigneeId(String paramString);
  
  void setAssigneeNames(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\task\IBpmTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */