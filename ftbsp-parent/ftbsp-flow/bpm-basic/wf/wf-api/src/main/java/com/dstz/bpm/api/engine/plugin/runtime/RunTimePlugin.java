package com.dstz.bpm.api.engine.plugin.runtime;

public interface RunTimePlugin<S, M, R> {
  R execute(S paramS, M paramM);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\runtime\RunTimePlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */