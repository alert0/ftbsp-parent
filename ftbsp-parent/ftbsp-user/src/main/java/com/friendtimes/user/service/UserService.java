package com.friendtimes.user.service;

import com.alibaba.fastjson.JSONObject;
import com.friendtimes.common.exception.CustomException;
import com.friendtimes.common.model.response.CommonCode;
import com.friendtimes.domain.user.ext.*;
import com.friendtimes.user.common.UserModule;
import com.friendtimes.user.dao.ModuleMapper;
import com.friendtimes.utils.HttpClientHelper;
import com.friendtimes.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.friendtimes.user.dao.CompanyUserRepository;
import com.friendtimes.user.dao.MenuMapper;
import com.friendtimes.user.dao.UserRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
@Slf4j
@PropertySource(value = {"classpath:application.properties"})
public class UserService {

    @Value("${data.url}")
    private String url;

    @Value("${data.secretId}")
    private String secretId;

    @Value("${data.secretKey}")
    private String secretKey;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyUserRepository companyUserRepository;

    @Autowired
    MenuMapper menuMapper;

    @Autowired
    ModuleMapper moduleMapper;


    // 根据账号查询xcUser信息
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 根据账号查询用户信息
    public UserExt getUserExt(String username) {
        // 根据账号查询User信息
        User user = this.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 用户id
        String userId = user.getId();
        // 查询用户所有权限
        List<Permissions> menus = menuMapper.selectPermissionByUserId(userId);

        // 根据用户id查询用户所属公司id
        // CompanyUser companyUser = companyUserRepository.findByUserId(userId);
        // 取到用户的公司id
        /*
         * String companyId = null; if (companyUser != null) { companyId =
         * companyUser.getCompanyId(); }
         */
        UserExt userExt = new UserExt();
        BeanUtils.copyProperties(user, userExt);
        // userExt.setCompanyId(companyId);
        // 设置权限
        userExt.setPermissions(menus);
        // userExt.setPassword(null);
        return userExt;
    }

    public List<Permissions> getUserPerm(String username) {
        // 根据账号查询User信息
        User user = this.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        // 用户id
        String userId = user.getId();
        // 查询用户所有权限
        List<Permissions> perm = menuMapper.selectPermissionByUserId(userId);
        return perm;
    }

    //查询用户专用功能
    public List<Module> getDedicatedModule(String username) {
        List<Module> modules = moduleMapper.getDedicatedModuleByUserName(username);
        return modules;
    }

    //查询用户常用功能
    public List<Module> getModules(String username) {
        List<Module> modules = moduleMapper.getModulesByUserName(username);
        return modules;
    }

    //查询用户信息
    public Map<String, String> getUserDetail(String username) {
        if (username == null || "".equals(username)) {
            log.info("SSO中未取到用户名，用户未登陆");
            //未取到用户名时，错误码信息“此操作需要登陆系统”
            throw new CustomException(CommonCode.UNAUTHENTICATED);
        }
        String name = "";
        String entryTime = "";
        String res = "";
        String status = "";
        //员工信息查询接口
//        String url = "http://192.168.10.132/zentao/api-v1.html";
        //账号信息
        String account = username;
        String action = "getUser";
        String timestamp = "" + System.currentTimeMillis() / 1000;
        /*String secretId = "309031BE8B5111E9B7DD005056A0202F";
        String secretKey = "MzA5MDMxRUI4QjUxMTFFOUI3REQwMDUwNTZBMDIwMkY=";*/
        String sign = MD5Util.getStringMD5("account=" + username + "&action=" + action + "&secretId=" + secretId + "&timestamp=" + timestamp + secretKey);
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("action", action);
        paramMap.put("account", account);
        paramMap.put("timestamp", timestamp);
        paramMap.put("secretId", secretId);
        paramMap.put("sign", sign);
        try {
            //访问第三方接口
            res = HttpClientHelper.postFormData(url, paramMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> jsonMap = (Map<String, Object>) JSONObject.parseObject(res);
        Object data = jsonMap.get("data");
        Map<String, String> dataMap = null;
        try {
            dataMap = (Map<String, String>)data;
        } catch (Exception e) {
            //用户名错误时，错误码信息“非法参数”
            log.info("用户名不存在，非法访问");
            throw new CustomException(CommonCode.INVALID_PARAM);

        }
        name = dataMap.get("name");
        entryTime = dataMap.get("entryTime");
        try {
            //获取员工新老信息
            status = judgeUserStatus(entryTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Map<String, String> userDetail = new HashMap<>();
        userDetail.put("name", name);
        userDetail.put("status", status);
        return userDetail;
    }

    //新增用户专用功能
    public void addModuleByIds(String[] idsArray, String name) {
        if (name == null || "".equals(name)) {
            //未取到用户名时，错误码信息“此操作需要登陆系统”
            log.info("SSO中未取到用户名，用户未登陆");
            throw new CustomException(CommonCode.UNAUTHENTICATED);
        }
        ArrayList<UserModule> list = new ArrayList<>();
        for (int i = 0; i < idsArray.length; i++) {
            list.add(new UserModule(name, idsArray[i]));
        }
        moduleMapper.addModuleByIds(list);
    }

    //判断员工为新老员工
    public String judgeUserStatus(String entryTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String nowTime = sdf.format(date);
        int monthBetween = (longOfTwoDate(sdf.parse(entryTime), sdf.parse(nowTime)))/30;
        if (monthBetween >= 6) {
            return "old";
        }
        return "new";
    }

    //计算两个日期间的天数差
    public static int longOfTwoDate(Date first, Date second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(first);
        int cnt = 0;
        while (calendar.getTime().compareTo(second) != 0) {
            calendar.add(Calendar.DATE, 1);
            cnt++;
        }
        return cnt;
    }

    public void deleteModuleByIds(String[] idsArray, String username) {
        List<String> ids = Arrays.asList(idsArray);
        moduleMapper.deleteModuleByIds(ids, username);
    }
}
