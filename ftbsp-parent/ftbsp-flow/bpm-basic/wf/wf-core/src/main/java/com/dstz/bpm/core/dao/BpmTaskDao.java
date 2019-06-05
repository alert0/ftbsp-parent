package com.dstz.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmTask;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmTaskDao extends BaseDao<String, BpmTask> {
  List<BpmTask> getByInstIdNodeId(@Param("instId") String paramString1, @Param("nodeId") String paramString2);
  
  List<BpmTask> getTodoList(QueryFilter paramQueryFilter);
  
  void removeByInstId(@Param("instId") String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\dao\BpmTaskDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */