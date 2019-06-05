package com.dstz.bpm.api.engine.action.cmd;

import com.dstz.bpm.api.model.inst.IBpmInstance;

public interface InstanceActionCmd extends ActionCmd {
  String getSubject();
  
  String getBusinessKey();
  
  String getInstanceId();
  
  IBpmInstance getBpmInstance();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\cmd\InstanceActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */