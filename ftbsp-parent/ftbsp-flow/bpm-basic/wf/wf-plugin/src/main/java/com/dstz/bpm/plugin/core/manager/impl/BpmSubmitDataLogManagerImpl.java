package com.dstz.bpm.plugin.core.manager.impl;

import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.plugin.core.dao.BpmSubmitDataLogDao;
import com.dstz.bpm.plugin.core.manager.BpmSubmitDataLogManager;
import com.dstz.bpm.plugin.core.model.BpmSubmitDataLog;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("bpmSubmitDataLogManager")
public class BpmSubmitDataLogManagerImpl extends BaseManager<String, BpmSubmitDataLog> implements BpmSubmitDataLogManager {
  @Resource
  BpmSubmitDataLogDao a;
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\core\manager\impl\BpmSubmitDataLogManagerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */