package com.dstz.bpm.core.manager;

import com.dstz.bpm.core.model.overallview.BpmOverallView;
import java.util.List;
import java.util.Map;

public interface BpmDefOverallManager {
	BpmOverallView getBpmOverallView(String paramString);

	void saveBpmOverallView(BpmOverallView paramBpmOverallView);

	Map<String, List<BpmOverallView>> importPreview(String paramString) throws Exception;

	void importSave(List<BpmOverallView> paramList);

	Map<String, String> exportBpmDefinitions(String... paramVarArgs) throws Exception;
}