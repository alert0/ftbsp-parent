package com.dstz.bpm.engine.plugin.cmd;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.ExecutionCommand;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExecutionPluginCommand implements ExecutionCommand {
	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	BpmProcessDefService a;

	public void execute(EventType eventType, InstanceActionCmd actionModel) {
		this.LOG.debug("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓【{}】插件执行开始↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", eventType.getValue());

		String defId = actionModel.getDefId();
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);

		this.LOG.trace("============【全局插件】========{}=========",
				Integer.valueOf(bpmProcessDef.getBpmPluginContexts().size()));
		for (BpmPluginContext bpmPluginContext : bpmProcessDef.getBpmPluginContexts()) {
			a(bpmPluginContext, actionModel, eventType);
		}

		BpmNodeDef nodeDef = null;
		if (eventType == EventType.START_POST_EVENT || eventType == EventType.START_EVENT) {
			nodeDef = this.a.getStartEvent(defId);
		} else if (eventType == EventType.END_EVENT || eventType == EventType.END_POST_EVENT
				|| eventType == EventType.MANUAL_END) {

			nodeDef = (BpmNodeDef) this.a.getEndEvents(defId).get(0);
		}

		if (nodeDef != null && nodeDef.getBpmPluginContexts() != null) {
			this.LOG.trace("============【{}插件】========{}=========", eventType.getValue(),
					Integer.valueOf(bpmProcessDef.getBpmPluginContexts().size()));
			for (BpmPluginContext bpmPluginContext : nodeDef.getBpmPluginContexts()) {
				a(bpmPluginContext, actionModel, eventType);
			}
		}

		this.LOG.debug("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑【{}】插件执行完毕↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑", eventType.getValue());
	}

	private void a(BpmPluginContext bpmPluginContext, InstanceActionCmd actionModel, EventType eventType) {
		BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();

		if (bpmPluginDef instanceof BpmExecutionPluginDef) {
			BpmExecutionPluginDef bpmExecutionPluginDef = (BpmExecutionPluginDef) bpmPluginDef;
			BpmExecutionPlugin<BpmExecutionPluginSession, BpmExecutionPluginDef> bpmExecutionPlugin = BpmPluginFactory
					.buildExecutionPlugin(bpmPluginContext, eventType);
			BpmExecutionPluginSession session = BpmPluginSessionFactory.buildExecutionPluginSession(actionModel,
					eventType);

			if (bpmExecutionPlugin != null)
				try {
					bpmExecutionPlugin.execute(session, bpmExecutionPluginDef);
					this.LOG.debug("==>执行插件【{}】", bpmPluginContext.getTitle());
				} catch (Exception e) {
					this.LOG.error("执行插件【{}】出现异常:{}", new Object[] { bpmPluginContext.getTitle(), e.getMessage(), e });
					throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
				}
		}
	}
}