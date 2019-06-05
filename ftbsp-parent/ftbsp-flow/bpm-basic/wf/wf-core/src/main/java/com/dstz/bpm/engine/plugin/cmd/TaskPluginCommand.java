package com.dstz.bpm.engine.plugin.cmd;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.plugin.factory.BpmPluginFactory;
import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskPluginCommand implements TaskCommand {
	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	BpmProcessDefService a;

	public void execute(EventType eventType, TaskActionCmd actionModel) {
		this.LOG.debug("↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓【{}】插件执行开始↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓", eventType.getValue());

		String defId = actionModel.getBpmTask().getDefId();

		BpmNodeDef bpmNodeDef = this.a.getBpmNodeDef(defId, actionModel.getNodeId());

		BpmExecutionPluginSession bpmTaskSession = BpmPluginSessionFactory.buildTaskPluginSession(actionModel,
				eventType);
		DefaultBpmExecutionPluginSession defaultBpmExecutionPluginSession = BpmPluginSessionFactory
				.buildExecutionPluginSession(actionModel, eventType);

		this.LOG.trace("============【节点插件】========{}=========",
				Integer.valueOf(bpmNodeDef.getBpmPluginContexts().size()));
		for (BpmPluginContext bpmPluginContext : bpmNodeDef.getBpmPluginContexts()) {
			BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
			if (bpmPluginDef instanceof com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef) {

				BpmExecutionPlugin bpmTaskPlugin = BpmPluginFactory.buildExecutionPlugin(bpmPluginContext, eventType);
				if (bpmTaskPlugin == null)
					continue;
				try {
					this.LOG.debug("==>执行任务插件【{}】", bpmPluginContext.getTitle());
					bpmTaskPlugin.execute(bpmTaskSession, bpmPluginDef);
				} catch (Exception e) {
					this.LOG.error("{}执行任务插件【{}】出现异常:{}",
							new Object[] { actionModel.getNodeId(), bpmPluginContext.getTitle(), e.getMessage(), e });
					throw new BusinessException(bpmPluginContext.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
				}
			}
		}

		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
		this.LOG.trace("============【全局插件】========{}=========",
				Integer.valueOf(bpmProcessDef.getBpmPluginContexts().size()));
		for (BpmPluginContext globalCtx : bpmProcessDef.getBpmPluginContexts()) {
			BpmExecutionPlugin bpmExecutionPlugin = BpmPluginFactory.buildExecutionPlugin(globalCtx, eventType);
			if (bpmExecutionPlugin != null) {
				try {
					this.LOG.debug("==>【{}】节点执行全局插件【{}】", bpmNodeDef.getName(), globalCtx.getTitle());
					bpmExecutionPlugin.execute(defaultBpmExecutionPluginSession, globalCtx.getBpmPluginDef());
				} catch (Exception e) {
					this.LOG.error("【{}】节点执行全局插件【{}】出现异常:{}",
							new Object[] { bpmNodeDef.getName(), globalCtx.getTitle(), e.getMessage(), e });
					throw new BusinessException(globalCtx.getTitle(), BpmStatusCode.PLUGIN_ERROR, e);
				}
			}
		}

		this.LOG.debug("↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑【{}】插件执行完毕↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑", eventType.getValue());
	}
}