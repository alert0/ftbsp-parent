package com.dstz.bpm.api.model.inst;

import java.util.Date;

public interface BpmExecutionStack {
  void setId(String paramString);
  
  String getId();
  
  void setTaskId(String paramString);
  
  String getTaskId();
  
  void setInstId(String paramString);
  
  String getInstId();
  
  void setParentId(String paramString);
  
  String getParentId();
  
  void setNodeId(String paramString);
  
  String getNodeId();
  
  void setNodeName(String paramString);
  
  String getNodeName();
  
  void setStartTime(Date paramDate);
  
  Date getStartTime();
  
  void setEndTime(Date paramDate);
  
  Date getEndTime();
  
  void setIsMulitiTask(Short paramShort);
  
  Short getIsMulitiTask();
  
  void setNodeType(String paramString);
  
  String getNodeType();
  
  String getActionName();
  
  void setActionName(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\inst\BpmExecutionStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */