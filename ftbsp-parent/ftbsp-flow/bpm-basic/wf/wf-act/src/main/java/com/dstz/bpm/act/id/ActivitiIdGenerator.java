package com.dstz.bpm.act.id;

import java.util.UUID;

//import com.dstz.base.core.id.IdGenerator;
import org.activiti.engine.impl.cfg.IdGenerator;

public class ActivitiIdGenerator implements IdGenerator {
	private com.dstz.base.core.id.IdGenerator f = null;

	public void setIdGenerator(com.dstz.base.core.id.IdGenerator idGenerator) {
		this.f = idGenerator;
	}

	public String getNextId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	// @Override
	public Long getUId() {
		return 1L;
	}

	// @Override
	public String getSuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}