/*   */ package com.dstz.bpm.api.model.inst;

import java.util.Date;

/*   */ 
/*   */ public interface IBpmInstance {
/* 4 */   public static final Short INSTANCE_FORBIDDEN = Short.valueOf((short)1);
/* 5 */   public static final Short INSTANCE_NO_FORBIDDEN = Short.valueOf((short)0);
/*   */   
/*   */   String getId();
/*   */   
/*   */   String getSubject();
/*   */   
/*   */   String getDefId();
/*   */   
/*   */   String getActDefId();
/*   */   
/*   */   String getDefKey();
/*   */   
/*   */   String getDefName();
/*   */   
/*   */   String getBizKey();
/*   */   
/*   */   String getStatus();
/*   */   
/*   */   Date getEndTime();
/*   */   
/*   */   Long getDuration();
/*   */   
/*   */   String getTypeId();
/*   */   
/*   */   String getActInstId();
/*   */   
/*   */   String getCreateBy();
/*   */   
/*   */   String getCreator();
/*   */   
/*   */   Date getCreateTime();
/*   */   
/*   */   String getCreateOrgId();
/*   */   
/*   */   String getUpdateBy();
/*   */   
/*   */   Date getUpdateTime();
/*   */   
/*   */   String getIsFormmal();
/*   */   
/*   */   String getParentInstId();
/*   */   
/*   */   Short getIsForbidden();
/*   */   
/*   */   String getDataMode();
/*   */   
/*   */   Integer getSupportMobile();
/*   */   
/*   */   String getSuperNodeId();
/*   */   
/*   */   Boolean hasCreate();
/*   */   
/*   */   void setHasCreate(Boolean paramBoolean);
/*   */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\inst\IBpmInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */