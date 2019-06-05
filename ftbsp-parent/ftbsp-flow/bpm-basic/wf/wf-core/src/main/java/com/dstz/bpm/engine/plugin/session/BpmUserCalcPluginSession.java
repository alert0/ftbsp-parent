package com.dstz.bpm.engine.plugin.session;

import com.dstz.bpm.api.model.task.IBpmTask;

public interface BpmUserCalcPluginSession extends BpmPluginSession {
  Boolean isPreViewModel();
  
  IBpmTask getBpmTask();
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\session\BpmUserCalcPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */