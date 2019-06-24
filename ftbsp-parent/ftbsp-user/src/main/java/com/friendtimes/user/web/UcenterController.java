package com.friendtimes.user.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.friendtimes.domain.user.ext.Module;
import com.friendtimes.domain.user.ext.ModuleData;
import com.friendtimes.domain.user.ext.response.ModuleResult;
import net.bojoy.sso.factory.SSOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.friendtimes.api.user.UserControllerApi;
import com.friendtimes.common.model.response.CommonCode;
import com.friendtimes.domain.user.ext.Permissions;
import com.friendtimes.domain.user.ext.UserExt;
import com.friendtimes.domain.user.ext.response.PermResult;
import com.friendtimes.user.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @version 1.0
 **/

@Slf4j
@RestController
@RequestMapping("/user")
public class UcenterController implements UserControllerApi {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Override
    public UserExt getUserext(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/permission")
    public PermResult getUserPerm(String username) {
        List<Permissions> permissions = userService.getUserPerm(username);
        return new PermResult(CommonCode.SUCCESS, permissions);
    }

    @Override
    @PostMapping("/logStash")
    public String logStash(HttpServletRequest re) {
        //HttpServletRequest r = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        log.info("请求开始, 各个参数, url: {}, method: {}, uri: {}, params: {},ip:{}", url, method, uri, queryString, request.getRemoteAddr());
        return "sucess";
    }

    @GetMapping("/module")
    public ModuleResult getModuleDtails(){
        String username = SSOFactory.getSsoUser(request).getUsername();
        System.out.println("------------"+request.getSession().getId()+"------------------------");
        List<Module> modules = userService.getModules(username);
        List<Module> dedicatedModule = userService.getDedicatedModule(username);
        Map<String, String> userDetail = userService.getUserDetail(username);
        String name = userDetail.get("name");
        String status = userDetail.get("status");
        return new ModuleResult(CommonCode.SUCCESS, new ModuleData(name, status, modules, dedicatedModule));
    }

	@PostMapping("/addModule")
	public ModuleResult getDedicatedModule(@RequestParam(required = true,defaultValue = "") String ids) {
		String username = SSOFactory.getSsoUser(request).getUsername();
        if (!"".equals(ids)) {
            String[] idsArray = ids.split(",");
            userService.addModuleByIds(idsArray,username);
        }
        List<Module> dedicatedModule = userService.getDedicatedModule(username);
        return new ModuleResult(CommonCode.SUCCESS, new ModuleData(dedicatedModule));
	}

    @PostMapping("/deleteModule")
    public ModuleResult deleteModuleByIds(@RequestParam(required = true,defaultValue = "") String ids) {
        String username = SSOFactory.getSsoUser(request).getUsername();
        if (!"".equals(ids)) {
            String[] idsArray = ids.split(",");
            userService.deleteModuleByIds(idsArray,username);
        }
        List<Module> dedicatedModule = userService.getDedicatedModule(username);
        return new ModuleResult(CommonCode.SUCCESS, new ModuleData(dedicatedModule));
    }
}
