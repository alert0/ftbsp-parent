package com.dstz.bpm.core.dao;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.dao.BaseDao;
import com.dstz.bpm.core.model.BpmDefinition;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface BpmDefinitionDao extends BaseDao<String, BpmDefinition> {
	/**
	 * 根据主版本IS_MAIN_='Y'和key_，查询bpm_definition表
	 * @param paramString
	 * @return
	 */
	BpmDefinition getMainByDefKey(String paramString);

	/**
	 * 根据DEPLOYMENT_ID_、NAME_更新ACT_GE_BYTEARRAY表BYTES_
	 * @param paramString1
	 * @param paramString2
	 * @param paramArrayOfByte
	 */
	void updateActResourceEntity(@Param("deploymentId") String paramString1, @Param("resName") String paramString2,
			@Param("bpmnBytes") byte[] paramArrayOfByte);

	/**
	 * 根据key查bpm_definition表
	 * 
	 * @param paramString
	 * @return
	 */
	List<BpmDefinition> getByKey(String paramString);

	/**
	 * 根据act_model_id_查bpm_definition表
	 * 
	 * @param paramString
	 * @return
	 */
	List<BpmDefinition> getDefByActModelId(String paramString);

	/**
	 * 根据act_model_id_查bpm_definition表
	 * 
	 * @param paramString
	 * @return
	 */
	BpmDefinition getByActDefId(String paramString);

	void updateToMain(String paramString);

	/**
	 * 根据QueryFilter条件，查bpm_definition表
	 * 
	 * @param paramQueryFilter
	 * @return
	 */
	List<BpmDefinition> getMyDefinitionList(QueryFilter paramQueryFilter);
}

/*
 * Location:
 * D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\
 * bpm\core\dao\BpmDefinitionDao.class Java compiler version: 8 (52.0) JD-Core
 * Version: 1.0.0
 */