package com.dstz.bpm.api.engine.action.cmd;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;
import java.util.Map;

public interface ActionCmd {
  public static final String DATA_MODE_PK = "pk";
  
  public static final String DATA_MODE_BO = "bo";
  
  Map<String, Object> getActionVariables();
  
  void setActionVariables(Map<String, Object> paramMap);
  
  Map<String, Object> getVariables();
  
  void addVariable(String paramString, Object paramObject);
  
  Object getVariable(String paramString);
  
  boolean hasVariable(String paramString);
  
  void removeVariable(String paramString);
  
  Map<String, List<SysIdentity>> getBpmIdentities();
  
  List<SysIdentity> getBpmIdentity(String paramString);
  
  void setBpmIdentity(String paramString, List<SysIdentity> paramList);
  
  String getDataMode();
  
  void setDataMode(String paramString);
  
  void setBusData(JSONObject paramJSONObject);
  
  JSONObject getBusData();
  
  String getBusinessKey();
  
  void setBusinessKey(String paramString);
  
  String getActionName();
  
  void setActionName(String paramString);
  
  String getDefId();
  
  IBpmInstance getBpmInstance();
  
  String getDestination();
  
  String getFormId();
  
  Map<String, IBusinessData> getBizDataMap();
  
  String executeCmd();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\cmd\ActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */