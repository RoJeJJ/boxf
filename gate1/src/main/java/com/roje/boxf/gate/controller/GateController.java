package com.roje.boxf.gate.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GateController {

    @Value("${eureka.instance.hostname}")
    private String host;

    @Value("${server.port}")
    private int port;

    @GetMapping("/server-info")
    public Map<String, Object> serverInfo(){
        Map<String,Object> result = new HashMap<>();
        result.put("host",host);
        result.put("port",port);
        return result;
    }
}
