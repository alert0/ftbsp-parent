package com.dstz.bpm.api.model.task;

import java.util.Date;

public interface IBpmTaskOpinion {
  String getId();
  
  String getInstId();
  
  String getSupInstId();
  
  String getTaskId();
  
  String getTaskKey();
  
  String getTaskName();
  
  String getToken();
  
  String getAssignInfo();
  
  String getApprover();
  
  String getApproverName();
  
  String getOpinion();
  
  String getStatus();
  
  String getFormId();
  
  String getCreateBy();
  
  Date getCreateTime();
  
  Date getApproveTime();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\task\IBpmTaskOpinion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */