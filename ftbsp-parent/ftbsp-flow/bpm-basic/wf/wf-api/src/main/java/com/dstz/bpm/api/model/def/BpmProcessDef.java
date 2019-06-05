package com.dstz.bpm.api.model.def;

import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import java.util.List;

public interface BpmProcessDef extends BpmDef {
  String getDefKey();
  
  String getName();
  
  String getProcessDefinitionId();
  
  List<BpmNodeDef> getBpmnNodeDefs();
  
  BpmProcessDef getParentProcessDef();
  
  BpmNodeDef getStartEvent();
  
  List<BpmNodeDef> getStartNodes();
  
  List<BpmNodeDef> getEndEvents();
  
  BpmNodeDef getBpmnNodeDef(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\def\BpmProcessDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */