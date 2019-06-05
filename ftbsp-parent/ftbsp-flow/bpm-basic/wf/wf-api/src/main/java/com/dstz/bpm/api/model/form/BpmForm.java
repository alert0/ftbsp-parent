package com.dstz.bpm.api.model.form;

import com.dstz.form.api.model.FormCategory;
import java.io.Serializable;

public interface BpmForm extends Serializable {
  String getName();
  
  void setName(String paramString);
  
  FormCategory getType();
  
  void setType(FormCategory paramFormCategory);
  
  String getFormValue();
  
  void setFormValue(String paramString);
  
  boolean isFormEmpty();
  
  String getFormHandler();
  
  void setFormHandler(String paramString);
  
  String getFormHtml();
  
  void setFormHtml(String paramString);
}


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\form\BpmForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */