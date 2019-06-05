package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmTaskStack;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmTaskStackDao extends BaseDao<String, BpmTaskStack> {
  BpmTaskStack getByTaskId(String paramString);
  
  void removeByInstanceId(String paramString);
  
  List<BpmTaskStack> getByInstanceId(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\dao\BpmTaskStackDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */