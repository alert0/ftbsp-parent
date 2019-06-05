/*     */ package com.dstz.bpm.api.engine.action.button;

/*     */
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.handler.ActionHandler;
/*     */ import com.dstz.bpm.api.engine.data.result.BpmFlowData;
/*     */ import com.dstz.bpm.api.engine.data.result.BpmFlowTaskData;
/*     */ import com.dstz.bpm.api.model.def.NodeProperties;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.Button;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class ButtonFactory
/*     */ {
	/*     */ public static List<Button> generateButtons(BpmNodeDef nodeDef, boolean isDefault)
			throws ClassNotFoundException {
		/* 37 */ NodeProperties prop = nodeDef.getNodeProperties();
		/*     */
		/* 39 */ List<Button> btns = new ArrayList<Button>();
		/*     */
		/* 41 */ Map<String, ActionHandler> actionMap = AppUtil.getImplInstance(ActionHandler.class);
		/*     */
		/* 43 */ List<ActionHandler> list = new ArrayList<ActionHandler>(actionMap.values());
		/* 44 */ sortActionList(list);
		/*     */
		/* 46 */ for (ActionHandler actionHandler : list) {
			/*     */
			/* 48 */ if (isDefault && !actionHandler.isDefault().booleanValue()) {
				/*     */ continue;
				/*     */ }
			/*     */
			/* 52 */ if (!actionHandler.isSupport(nodeDef).booleanValue()) {
				/*     */ continue;
				/*     */ }
			/*     */
			/* 56 */ ActionType actionType = actionHandler.getActionType();
			/* 57 */ Button button = new Button(actionType.getName(), actionType.getKey(),
					actionHandler.getDefaultGroovyScript(), actionHandler.getConfigPage());
			/* 58 */ button.setBeforeScript(actionHandler.getDefaultBeforeScript());
			/*     */
			/* 60 */ btns.add(button);
			/*     */ }
		/*     */
		/* 63 */ return btns;
		/*     */ }

	/*     */
	/*     */
	/*     */ private static void sortActionList(List<ActionHandler> list) {
		/* 68 */ Collections.sort(list, new Comparator<ActionHandler>()
		/*     */ {
			/*     */ public int compare(ActionHandler o1, ActionHandler o2) {
				/* 71 */ return o1.getSn() - o2.getSn();
				/*     */ }
			/*     */ });
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public static List<Button> getInstanceButtons() {
		/* 81 */ ArrayList<Button> btns = new ArrayList<Button>();
		/*     */
		/* 83 */ Map<String, ActionHandler> actionMap = AppUtil.getImplInstance(ActionHandler.class);
		/* 84 */ List<ActionHandler> list = new ArrayList<ActionHandler>(actionMap.values());
		/*     */
		/* 86 */ for (ActionHandler actionHandler : list) {
			/* 87 */ ActionType actionType = actionHandler.getActionType();
			/* 88 */ if (ActionType.FLOWIMAGE != actionType && ActionType.PRINT != actionType
					&& ActionType.TASKOPINION != actionType && actionType != ActionType.REMINDER) {
				/*     */ continue;
				/*     */ }
			/*     */
			/* 92 */ Button button = new Button(actionType.getName(), actionType.getKey(),
					actionHandler.getDefaultGroovyScript(), actionHandler.getConfigPage());
			/* 93 */ button.setBeforeScript(actionHandler.getDefaultBeforeScript());
			/*     */
			/* 95 */ btns.add(button);
			/*     */ }
		/*     */
		/* 98 */ return btns;
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */
	/*     */ public static List<Button> specialTaskBtnHandler(List<Button> btns, BpmFlowData flowData) {
		/* 108 */ if (!(flowData instanceof BpmFlowTaskData))
			return btns;
		/*     */
		/* 110 */ IBpmTask task = ((BpmFlowTaskData) flowData).getTask();
		/*     */
		/* 112 */ ButtonChecker checker = (ButtonChecker) AppUtil.getBean(task.getTaskType() + "ButtonChecker");
		/* 113 */ if (checker == null)
			return btns;
		/*     */
		/* 115 */ List<Button> buttons = new ArrayList<Button>();
		/* 116 */ for (Button btn : btns) {
			/* 117 */ if (checker.isSupport(btn)) {
				/* 118 */ buttons.add(btn);
				/*     */ }
			/*     */ }
		/*     */
		/* 122 */ checker.specialBtnHandler(buttons);
		/*     */
		/* 124 */ return buttons;
		/*     */ }
	/*     */ }

/*
 * Location:
 * D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\
 * api\engine\action\button\ButtonFactory.class Java compiler version: 8 (52.0)
 * JD-Core Version: 1.0.0
 */