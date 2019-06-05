package com.dstz.bpm.rest.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ZipUtil;
import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.response.impl.ResultMsg;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.core.util.ThreadMsgUtil;
import com.dstz.base.rest.ControllerTools;
import com.dstz.base.rest.util.RequestUtil;
import com.dstz.bpm.core.manager.BpmDefOverallManager;
import com.dstz.bpm.core.model.overallview.BpmOverallView;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping({ "/bpm/overallView" })
public class BpmOverallViewController extends ControllerTools {
	@Resource
	BpmDefOverallManager bpmDefOverallManager;

	@RequestMapping({ "getOverallView" })
	public ResultMsg<BpmOverallView> getOverallView(@RequestParam String defId) throws Exception {
		BpmOverallView ovrallView = this.bpmDefOverallManager.getBpmOverallView(defId);

		return getSuccessResult(ovrallView);
	}

	@RequestMapping({ "overallViewSave" })
	@CatchErr
	public ResultMsg overallViewSave(@RequestBody BpmOverallView overAllView) throws Exception {
		this.bpmDefOverallManager.saveBpmOverallView(overAllView);
		return getSuccessResult("保存成功！");
	}

	@RequestMapping({ "exportBpmDefinition" })
	@CatchErr("下载失败")
	public void exportXml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] defIds = RequestUtil.getStringAryByStr(request, "defIds");
		if (ArrayUtil.isEmpty(defIds))
			return;
		String zipName = "agileBpmDefs" + DateUtil.format(new Date(), "yyyy_MMdd_HHmm");

		Map<String, String> exprotXml = this.bpmDefOverallManager.exportBpmDefinitions(defIds);

		downLoadFile(request, response, exprotXml, zipName);
	}

	private static String getWebContextPath() {
		String path = ClassUtil.getClassPath();
		path = path.substring(0, path.lastIndexOf("WEB-INF"));
		return StringUtil.trimSuffix(path, "/");
	}

	private static final String ROOT_PATH = "temp" + File.separator + "tempZip";

	private static void downLoadFile(HttpServletRequest request, HttpServletResponse response,
			Map<String, String> fileContentMap, String zipName) throws Exception {
		String zipPath = getWebContextPath() + ROOT_PATH + File.separator + zipName;
		for (Map.Entry<String, String> ent : fileContentMap.entrySet()) {
			String fileName = (String) ent.getKey();
			String content = (String) ent.getValue();

			String filePath = zipPath + File.separator + fileName;

			FileUtil.writeUtf8String(content, filePath);
		}

		File file = ZipUtil.zip(zipPath);
		FileUtil.del(zipPath);

		RequestUtil.downLoadFile(response, file);
		FileUtil.del(file);
	}

	@RequestMapping({ "importPreview" })
	@CatchErr
	public ResultMsg<Map<String, List<BpmOverallView>>> importPreview(MultipartHttpServletRequest request,
			HttpServletResponse response) throws Exception {
		MultipartFile fileLoad = request.getFile("xmlFile");
		String name = fileLoad.getOriginalFilename();
		String unZipFilePath = getWebContextPath() + File.separator + "temp" + File.separator + "unzip";
		try {
			String fileDir = StringUtils.substringBeforeLast(name, ".");

			String originalFilename = fileLoad.getOriginalFilename();
			String destPath = unZipFilePath + File.separator + originalFilename;
			File file = FileUtil.mkdir(destPath);
			fileLoad.transferTo(file);

			ZipUtil.unzip(file);

			unZipFilePath = unZipFilePath + File.separator + fileDir;

			String flowXml = FileUtil.readUtf8String(unZipFilePath + "/agilebpm-flow.xml");
			if (StringUtil.isEmpty(flowXml)) {
				throw new BusinessException("导入的文件缺少 流程信息 agilebpm-flow.xml");
			}

			checkXmlFormat(flowXml);
			Map<String, List<BpmOverallView>> perviewMaps = this.bpmDefOverallManager.importPreview(flowXml);

			return getSuccessResult(perviewMaps);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			File unzipfile = new File(unZipFilePath);
			if (unzipfile.exists()) {
				FileUtil.del(unzipfile);
			}
		}
	}

	@RequestMapping({ "importSave" })
	@CatchErr
	public ResultMsg<String> importSave(@RequestBody List<BpmOverallView> overAllView) throws Exception {
		if (CollectionUtil.isEmpty(overAllView)) {
			throw new RuntimeException("导入的数据不能为空！");
		}

		this.bpmDefOverallManager.importSave(overAllView);

		return getSuccessResult("导入成功!<br/>" + ThreadMsgUtil.getMessage(true, "<br/>"));
	}

	private void checkXmlFormat(String xml) throws Exception {
		String firstName = "agilebpmXmlList";
		String nextName = "agilebpmXml";
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		String msg = "导入文件格式不对";
		if (!root.getName().equals(firstName))
			throw new Exception(msg);
		List<Element> itemLists = root.elements();
		for (Element elm : itemLists) {
			if (!elm.getName().equals(nextName))
				throw new Exception(msg);
		}
	}
}