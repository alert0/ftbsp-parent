package com.dstz.bpm.api.engine.context;

import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.model.def.BpmProcessDef;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BpmContext {
	private static ThreadLocal<Stack<ActionCmd>> threadActionModel = new ThreadLocal();
	private static ThreadLocal<Map<String, BpmProcessDef>> threadBpmProcessDef = new ThreadLocal();
	private static Stack<ActionCmd> stack;

	public static void setActionModel(ActionCmd actionModel) {
		getStack(threadActionModel).push(actionModel);
	}

	public static ActionCmd getActionModel() {
		stack = getStack(threadActionModel);
		if (stack.isEmpty()) {
			return null;
		}
		return (ActionCmd) stack.peek();
	}

	public static ActionCmd submitActionModel() {
		stack = getStack(threadActionModel);
		if (stack.isEmpty()) {
			return null;
		}
		return (ActionCmd) stack.firstElement();
	}

	public static void removeActionModel() {
		stack = getStack(threadActionModel);
		if (!stack.isEmpty()) {
			stack.pop();
		}
	}
	// 获取某个线程共享变量
	public static BpmProcessDef getProcessDef(String defId) {
		Map<String, BpmProcessDef> map = getThreadMap(threadBpmProcessDef);
		return (BpmProcessDef) map.get(defId);
	}

	public static void addProcessDef(String defId, BpmProcessDef processDef) {
		getThreadMap(threadBpmProcessDef).put(defId, processDef);
	}

	public static void cleanTread() {
		threadActionModel.remove();
		threadBpmProcessDef.remove();
	}

	protected static <T> Stack<T> getStack(ThreadLocal<Stack<T>> threadLocal) {
		Stack<T> stack = (Stack) threadLocal.get();
		if (stack == null) {
			stack = new Stack<T>();
			threadLocal.set(stack);
		}
		return stack;
	}

	protected static <T> Map<String, T> getThreadMap(ThreadLocal<Map<String, T>> threadMap) {
		Map<String, T> processDefMap = (Map) threadMap.get();
		if (processDefMap == null) {
			processDefMap = new HashMap<String, T>();
			threadMap.set(processDefMap);
		}
		return processDefMap;
	}
}