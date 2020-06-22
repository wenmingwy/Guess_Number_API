package com.twschool.practice.api;


import com.twschool.practice.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Author Administrator
 * @Date 2020/6/22 15:41
 * @Version 1.0
 */
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @PostMapping("/games/guess-numbers")
    public Map<String,String> guess(@RequestBody Map<String,String> requestBody) throws Exception {

        String number = requestBody.get("number");


        String result = gameService.guess(number);


        Map<String,String> responseBody = new HashMap<>();
        responseBody.put("input",number);
        responseBody.put("result",result);

        return responseBody;
    }
}
