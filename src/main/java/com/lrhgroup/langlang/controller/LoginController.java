package com.lrhgroup.langlang.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lrhgroup.langlang.repository.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 登录功能的控制类
 * @author jyf
 * @version 1.0
 * @date 2019/5/19 16:41
 */
@Controller
public class LoginController {

    @Resource
    private UserMapper userMapper;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public String loginConfirm(@RequestBody JSONObject UserJson) {
        JSONObject loginResultJson = new JSONObject();
        String userAccount = UserJson.get("userAccount").toString();
        String userPassword = UserJson.get("userPassword").toString();
        if (userAccount.equals("") || userPassword.equals("")){
            loginResultJson.put("code","-1");
            loginResultJson.put("msg","用户名或密码为空，登录失败");
            return loginResultJson.toJSONString();
        }
        try {
            String userName = userMapper.queryLogin(userAccount,userPassword);
            if (userName == null) {
                loginResultJson.put("code","0");
                loginResultJson.put("msg","用户名或密码错误，登录失败");
                return loginResultJson.toJSONString();
            } else {
                loginResultJson.put("code","1");
                loginResultJson.put("userName",userName);
                return loginResultJson.toJSONString();
            }
        } catch (Exception e) {
            loginResultJson.put("code","-2");
            loginResultJson.put("msg","数据查询失败");
            return loginResultJson.toJSONString();
        }
    }

}
