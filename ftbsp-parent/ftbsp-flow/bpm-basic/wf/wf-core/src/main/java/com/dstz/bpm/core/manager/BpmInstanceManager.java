package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.def.IBpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskApprove;
import java.util.List;

public interface BpmInstanceManager extends Manager<String, BpmInstance> {
	boolean isSuspendByInstId(String paramString);

	List<BpmInstance> getApplyList(String paramString, QueryFilter paramQueryFilter);

	List<BpmTaskApprove> getApproveHistoryList(String paramString, QueryFilter paramQueryFilter);

	BpmInstance getTopInstance(BpmInstance paramBpmInstance);

	BpmInstance genInstanceByDefinition(IBpmDefinition paramIBpmDefinition);

	List<BpmInstance> getByPId(String paramString);

	BpmInstance getByActInstId(String paramString);

	void delete(String paramString);
}