package com.dstz.bpm.api.engine.action.button;

import com.dstz.bpm.api.model.nodedef.Button;
import java.util.List;

public interface ButtonChecker {
  boolean isSupport(Button paramButton);
  
  void specialBtnHandler(List<Button> paramList);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\button\ButtonChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */