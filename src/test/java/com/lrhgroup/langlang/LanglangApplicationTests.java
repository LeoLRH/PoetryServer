package com.lrhgroup.langlang;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lrhgroup.langlang.controller.LoginController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LanglangApplication.class)
@WebAppConfiguration
public class LanglangApplicationTests {

    @Autowired
    private LoginController loginController;

    private JSONObject getUserJson(String userAccount, String userPassword){
        JSONObject testUser = new JSONObject();
        testUser.put("userAccount",userAccount);
        testUser.put("userPassword",userPassword);
        return testUser;
    }

    @Test
    public void testInvalidLogin() {
        JSONObject testUser = getUserJson("123","123");
        JSONObject resultJson = JSON.parseObject(loginController.loginConfirm(testUser));
        assert (Integer.parseInt(resultJson.get("code").toString()) == 0);
    }

    @Test
    public void testValidLogin(){
        JSONObject testUser = getUserJson("123456","123456");
        JSONObject resultJson = JSON.parseObject(loginController.loginConfirm(testUser));
        assert (Integer.parseInt(resultJson.get("code").toString()) == 1);
    }

    @Test
    public void testNullParamLogin(){
        JSONObject testUser = getUserJson("","123");
        JSONObject resultJson = JSON.parseObject(loginController.loginConfirm(testUser));
        assert (Integer.parseInt(resultJson.get("code").toString()) == -1);
        testUser = getUserJson("123","");
        resultJson = JSON.parseObject(loginController.loginConfirm(testUser));
        assert (Integer.parseInt(resultJson.get("code").toString()) == -1);
    }

}
