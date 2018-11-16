package com.roje.boxf.hall.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.boxf.constant.response.RespData;
import com.roje.boxf.hall.service.UserService;
import com.roje.boxf.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    private final UserService userService;

    private final RestTemplate restTemplate;

    public LoginController(UserService userService,
                           RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/login/account")
    public String login(String account, String password){
        User user = userService.getUserByAccount(account);
        RespData respData = checkUser(user,password);
        JsonObject data = new JsonObject();
        if (respData != RespData.SUCCESS) {
            data.addProperty("code",respData.getCode());
            data.addProperty("msg",respData.getMsg());
            return data.toString();
        }else {
            String gateInfo = restTemplate.getForObject("http://gate/server-info",String.class);
            if (gateInfo == null){
                data.addProperty("code",RespData.LOGIN___GATE_NOT_FOUND.getCode());
                data.addProperty("msg",RespData.LOGIN___GATE_NOT_FOUND.getMsg());
                return data.toString();
            }else {
                JsonElement gateData = new JsonParser().parse(gateInfo);
                data.addProperty("code",0);
                data.add("gate",gateData);
                return data.toString();
            }
        }
    }

    @PostMapping(value = "/register")
    public String register(String account,String password,HttpServletRequest request){
        User user = userService.getUserByAccount(account);
        JsonObject data = new JsonObject();
        if (user != null) {
            data.addProperty("code",RespData.REGISTER_ACCOUNT_EXISTS.getCode());
            data.addProperty("msg",RespData.REGISTER_ACCOUNT_EXISTS.getMsg());
            return data.toString();
        }
        user = createUser(account,password,request);
        userService.save(user);
        data.addProperty("code",RespData.SUCCESS.getCode());
        data.addProperty("msg",RespData.SUCCESS.getMsg());
        return data.toString();
    }

    private RespData checkUser(User user,String password) {
        if (user == null || !password.equals(user.getPassword()))
            return RespData.LOGIN___ERROR_ACCOUNT_OR_PASSWORD;
        if (user.getAccountStatus() == 1)
            return RespData.LOGIN___ACCOUNT_FORBIDDEN;
        return RespData.SUCCESS;
    }

    private User createUser(String account,String password,HttpServletRequest httpRequest){
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setIpConfig(httpRequest.getRemoteHost());
        user.setGold(10000);
        user.setRoomCard(12);
        user.setRegDate(new Date());
        user.setSex(0);
        return user;
    }
}
