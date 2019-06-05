package com.dstz.bpm.api.model.nodedef;

import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmDef;
import com.dstz.bpm.api.model.def.BpmProcessDef;
import com.dstz.bpm.api.model.def.NodeProperties;
import com.dstz.bpm.api.model.form.BpmForm;
import java.io.Serializable;
import java.util.List;

public interface BpmNodeDef extends Serializable, BpmDef {
  String getNodeId();
  
  String getName();
  
  NodeType getType();
  
  void setType(NodeType paramNodeType);
  
  List<BpmNodeDef> getIncomeNodes();
  
  List<BpmNodeDef> getOutcomeNodes();
  
  List<BpmNodeDef> getOutcomeTaskNodes();
  
  List<BpmNodeDef> getInnerOutcomeTaskNodes(boolean paramBoolean);
  
  List<BpmPluginContext> getBpmPluginContexts();
  
  BpmProcessDef getBpmProcessDef();
  
  String getAttribute(String paramString);
  
  void addIncomeNode(BpmNodeDef paramBpmNodeDef);
  
  void addOutcomeNode(BpmNodeDef paramBpmNodeDef);
  
  BpmNodeDef getParentBpmNodeDef();
  
  String getRealPath();
  
  BpmProcessDef getRootProcessDef();
  
  BpmForm getForm();
  
  BpmForm getMobileForm();
  
  <T> T getPluginContext(Class<T> paramClass);
  
  NodeProperties getNodeProperties();
  
  List<Button> getButtons();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\nodedef\BpmNodeDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */