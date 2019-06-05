package com.dstz.bpm.api.service;

import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.model.def.BpmProcessDef;
import com.dstz.bpm.api.model.def.IBpmDefinition;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import java.util.List;

public interface BpmProcessDefService {
  IBpmDefinition getDefinitionById(String paramString);
  
  IBpmDefinition getDefinitionByActDefId(String paramString);
  
  BpmNodeDef getBpmNodeDef(String paramString1, String paramString2);
  
  BpmProcessDef getBpmProcessDef(String paramString);
  
  List<BpmNodeDef> getNodeDefs(String paramString);
  
  List<BpmNodeDef> getNodesByType(String paramString, NodeType paramNodeType);
  
  List<BpmNodeDef> getAllNodeDef(String paramString);
  
  void clean(String paramString);
  
  BpmNodeDef getStartEvent(String paramString);
  
  List<BpmNodeDef> getEndEvents(String paramString);
  
  List<BpmNodeDef> getStartNodes(String paramString);
  
  boolean isStartNode(String paramString1, String paramString2);
  
  boolean validNodeDefType(String paramString1, String paramString2, NodeType paramNodeType);
  
  boolean isContainCallActivity(String paramString);
  
  List<BpmNodeDef> getSignUserNode(String paramString);
  
  BpmProcessDef initBpmProcessDef(IBpmDefinition paramIBpmDefinition);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\service\BpmProcessDefService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */