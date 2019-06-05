package com.dstz.bpm.core.dao;

import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.TaskIdentityLink;
import java.util.List;
import java.util.Set;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface TaskIdentityLinkDao extends BaseDao<String, TaskIdentityLink> {
  void removeByInstId(String paramString);
  
  void removeByTaskId(String paramString);
  
  void bulkCreate(List<TaskIdentityLink> paramList);
  
  int checkUserOperatorPermission(@Param("rights") Set<String> paramSet, @Param("taskId") String paramString1, @Param("instanceId") String paramString2);
  
  List<TaskIdentityLink> getByTaskId(String paramString);
  
  int queryTaskIdentityCount(String paramString);
  
  int queryInstanceIdentityCount(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\dao\TaskIdentityLinkDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */