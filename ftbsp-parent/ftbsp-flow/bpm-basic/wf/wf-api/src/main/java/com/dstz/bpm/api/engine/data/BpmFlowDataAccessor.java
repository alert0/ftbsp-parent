package com.dstz.bpm.api.engine.data;

import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bpm.api.engine.data.result.BpmFlowInstanceData;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.form.api.model.FormType;
import java.util.Map;

public interface BpmFlowDataAccessor {
  BpmFlowInstanceData getInstanceData(String paramString1, FormType paramFormType, String paramString2);
  
  Map<String, IBusinessData> getTaskBusData(String paramString);
  
  BpmFlowData getStartFlowData(String paramString1, String paramString2, FormType paramFormType, Boolean paramBoolean);
  
  BpmFlowData getFlowTaskData(String paramString, FormType paramFormType);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\data\BpmFlowDataAccessor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */