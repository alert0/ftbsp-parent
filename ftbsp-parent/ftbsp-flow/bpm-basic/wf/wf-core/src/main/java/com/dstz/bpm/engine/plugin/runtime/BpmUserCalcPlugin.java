package com.dstz.bpm.engine.plugin.runtime;

import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;

public interface BpmUserCalcPlugin<M extends BpmPluginDef> extends RunTimePlugin<BpmUserCalcPluginSession, M, List<SysIdentity>> {}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\runtime\BpmUserCalcPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */