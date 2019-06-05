package com.dstz.bpm.api.model.def;

import com.dstz.base.api.model.IBaseModel;
import java.util.Date;

public interface IBpmDefinition extends IBaseModel {
  String getId();
  
  String getName();
  
  String getKey();
  
  String getDesc();
  
  String getTypeId();
  
  String getStatus();
  
  void setActDefId(String paramString);
  
  String getActDefId();
  
  void setActModelId(String paramString);
  
  String getActModelId();
  
  void setActDeployId(String paramString);
  
  String getActDeployId();
  
  void setVersion(Integer paramInteger);
  
  Integer getVersion();
  
  void setMainDefId(String paramString);
  
  String getMainDefId();
  
  void setIsMain(String paramString);
  
  String getIsMain();
  
  void setCreateBy(String paramString);
  
  String getCreateBy();
  
  void setCreateTime(Date paramDate);
  
  Date getCreateTime();
  
  void setCreateOrgId(String paramString);
  
  String getCreateOrgId();
  
  void setUpdateBy(String paramString);
  
  String getUpdateBy();
  
  void setUpdateTime(Date paramDate);
  
  Date getUpdateTime();
  
  Integer getSupportMobile();
  
  String getDefSetting();
  
  Integer getRev();
  
  public static final class STATUS {
    public static final String DRAFT = "draft";
    
    public static final String DEPLOY = "deploy";
    
    public static final String FORBIDDEN = "forbidden";
  }
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\def\IBpmDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */