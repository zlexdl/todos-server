package com.todo.demo.controller;

import com.todo.demo.common.CommonResult;
import com.todo.demo.model.BaseInfo;
import com.todo.demo.model.Todos;
import com.todo.demo.model.Token;
import com.todo.demo.model.User;
import com.todo.demo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/v1/todo")
public class TodoController {
    private static final Logger log = LoggerFactory.getLogger(TodoController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Value("${host.ddpurse}")
    private String HOST_MALL_ADMIN;
    @Value("${app.secret}")
    private String SECRET = "";
    @Value("${app.id}")
    private String APPID = "92d251abf8281c178619";
    @Autowired
    private TodoRepository todoRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Todos createTodos(@RequestBody Todos todos) {
        log.info("todos: {}" , todos.toString());
        return todoRepository.save(todos);
    }

    @GetMapping("")
    public Todos getTodos(String address){
        return todoRepository.findById(address).orElse(null);
    }

    @GetMapping("/getSecret")
    public String getSecret(){
        return DigestUtils.md5DigestAsHex(SECRET.getBytes());
    }

    @GetMapping("/address/{code}")
    public String getAddress(@PathVariable String code){
        String url = HOST_MALL_ADMIN + "/access_token";
        BaseInfo info = new BaseInfo();
        info.setApp_id(APPID);
        info.setCode(code);
        info.setSecret(SECRET);
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(url, info, CommonResult.class);
        CommonResult commonResult = responseEntity.getBody();
        long returnCode = commonResult.getCode();
        if (returnCode != 0) return "";
        log.info("returnCode: {}" , returnCode);
        Map<String, String> token = (LinkedHashMap<String, String>)responseEntity.getBody().getData();

        String accessToken = token.get("access_token");
        url = HOST_MALL_ADMIN + "/get_user_info?access_token={token}";
        ResponseEntity<CommonResult> userResponse = restTemplate.getForEntity(url, CommonResult.class, accessToken);
        log.info("USER: {}" , userResponse.toString());
        Map<String, String> user = (LinkedHashMap<String, String>)userResponse.getBody().getData();
        String address = user.get("user_address");
        String userId = user.get("user_open_id");
        return userId;
    }

}
