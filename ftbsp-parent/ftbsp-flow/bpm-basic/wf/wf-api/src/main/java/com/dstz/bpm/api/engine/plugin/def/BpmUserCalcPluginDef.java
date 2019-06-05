package com.dstz.bpm.api.engine.plugin.def;

import com.dstz.bpm.api.constant.ExtractType;
import com.dstz.bpm.api.engine.constant.LogicType;

public interface BpmUserCalcPluginDef extends BpmPluginDef {
  ExtractType getExtract();
  
  void setExtract(ExtractType paramExtractType);
  
  LogicType getLogicCal();
  
  void setLogicCal(LogicType paramLogicType);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\def\BpmUserCalcPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */