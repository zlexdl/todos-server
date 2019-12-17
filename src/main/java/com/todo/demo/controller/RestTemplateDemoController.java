package com.todo.demo.controller;


import com.todo.demo.common.CommonResult;
import com.todo.demo.model.BaseInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/template")
public class RestTemplateDemoController {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${host.ddpurse}")
    private String HOST_MALL_ADMIN;
    @Value("${app.secret}")
    private String SECRET = "";
    @Value("${app.id}")
    private String APPID = "";

    @RequestMapping(value = "/get/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object getForEntity(@PathVariable String token) {
        String url = HOST_MALL_ADMIN + "/get_user_info?access_token={id}";
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(url, CommonResult.class, token);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/check/{token}", method = RequestMethod.GET)
    @ResponseBody
    public Object getForEntity2(@PathVariable String token) {
        String url = HOST_MALL_ADMIN + "/check_access_token/?access_token={token}";
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(url, CommonResult.class, token);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/get3/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getForEntity3(@PathVariable Long id) {
        String url = HOST_MALL_ADMIN + "/brand/{id}";
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(url).build().expand(id).encode();
        ResponseEntity<CommonResult> responseEntity = restTemplate.getForEntity(uriComponents.toUri(), CommonResult.class);
        return responseEntity.getBody();
    }

    @RequestMapping(value = "/get4/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getForObject(@PathVariable Long id) {
        String url = HOST_MALL_ADMIN + "/brand/{id}";
        CommonResult commonResult = restTemplate.getForObject(url, CommonResult.class, id);
        return commonResult;
    }

    @RequestMapping(value = "/post/{code}", method = RequestMethod.POST)
    @ResponseBody
    public Object postForEntity(@PathVariable String code) {
        String url = HOST_MALL_ADMIN + "/access_token";
        BaseInfo info = new BaseInfo();
        info.setApp_id(APPID);
        info.setCode(code);
        info.setSecret(SECRET);
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(url, info, CommonResult.class);
        return responseEntity.getBody();
    }

//    @ApiOperation("postForEntity jsonBody")
//    @RequestMapping(value = "/post2", method = RequestMethod.POST)
//    @ResponseBody
//    public Object postForObject(@RequestBody PmsBrand brand) {
//        String url = HOST_MALL_ADMIN + "/brand/create";
//        CommonResult commonResult = restTemplate.postForObject(url, brand, CommonResult.class);
//        return commonResult;
//    }

    @RequestMapping(value = "/post3", method = RequestMethod.POST)
    @ResponseBody
    public Object postForEntity3(@RequestParam String name) {
        String url = HOST_MALL_ADMIN + "/productAttribute/category/create";
        //设置头信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //构造表单参数
        MultiValueMap<String, String> params= new LinkedMultiValueMap<>();
        params.add("name", name);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(url, requestEntity, CommonResult.class);
        return responseEntity.getBody();
    }
}
