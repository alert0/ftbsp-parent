package com.dstz.bpm.api.engine.data.result;

import com.alibaba.fastjson.JSONObject;
import com.dstz.bpm.api.model.form.BpmForm;
import com.dstz.bpm.api.model.nodedef.Button;
import java.util.List;

public interface FlowData {
  String getDefId();
  
  BpmForm getForm();
  
  JSONObject getData();
  
  JSONObject getPermission();
  
  JSONObject getTablePermission();
  
  JSONObject getInitData();
  
  List<Button> getButtonList();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\data\result\FlowData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */