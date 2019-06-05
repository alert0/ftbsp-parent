package com.dstz.bpm.api.engine.plugin.cmd;

import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;

public interface ExecutionCommand {
  void execute(EventType paramEventType, InstanceActionCmd paramInstanceActionCmd);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\cmd\ExecutionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */