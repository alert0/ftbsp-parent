package com.dstz.bpm.act.service;

import java.util.Collection;
import java.util.Map;
import org.activiti.engine.runtime.ProcessInstance;

public interface ActInstanceService {
  String startProcessInstance(String paramString1, String paramString2, Map<String, Object> paramMap);
  
  String startProcessInstance(String paramString1, String paramString2, Map<String, Object> paramMap, String... paramVarArgs);
  
  Map<String, Object> getVariables(String paramString);
  
  void setVariable(String paramString1, String paramString2, Object paramObject);
  
  void setVariables(String paramString, Map<String, ? extends Object> paramMap);
  
  void removeVariable(String paramString1, String paramString2);
  
  void removeVariables(String paramString, Collection<String> paramCollection);
  
  boolean hasVariable(String paramString1, String paramString2);
  
  Object getVariable(String paramString1, String paramString2);
  
  Map<String, Object> getVariables(String paramString, Collection<String> paramCollection);
  
  void endProcessInstance(String paramString);
  
  void deleteProcessInstance(String paramString1, String paramString2);
  
  Object getSuperVariable(String paramString1, String paramString2);
  
  ProcessInstance getProcessInstance(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\service\ActInstanceService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */