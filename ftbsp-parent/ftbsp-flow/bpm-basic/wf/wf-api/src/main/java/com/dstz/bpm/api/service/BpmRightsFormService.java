package com.dstz.bpm.api.service;

import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.form.api.model.FormType;
import java.util.Set;

public interface BpmRightsFormService {
  IBusinessPermission getInstanceFormPermission(BpmFlowData paramBpmFlowData, String paramString, FormType paramFormType, boolean paramBoolean);
  
  IBusinessPermission getNodeSavePermission(String paramString1, String paramString2, Set<String> paramSet);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\service\BpmRightsFormService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */