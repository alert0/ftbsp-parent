package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;

public interface BpmExecutionPlugin<S extends BpmExecutionPluginSession, M extends BpmExecutionPluginDef> extends RunTimePlugin<S, M, Void> {}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\runtime\BpmExecutionPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */