package com.twschool.practice.api;

import com.twschool.practice.domain.*;
import com.twschool.practice.service.GameService;
import com.twschool.practice.service.PointComputeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("")
public class GuessNumberController {


    List<UserInfo> userInfoLists= new ArrayList<UserInfo>();

    @GetMapping("/guessGame")
    public int oneGameByOneUser(@RequestParam UserInfo userInfo, @RequestParam String guessNumber){

        UserInfo userInfoAll = judgeSamePerson(userInfo);
        PointComputeService pointComputeService = new PointComputeService();
        AnswerGenerator answerGenerator = new AnswerGenerator();
        GuessNumberGame guessNumberGame = new GuessNumberGame(answerGenerator);
        String result = guessNumberGame.guess(guessNumber);
        if (result.equals(GameStatus.SUCCEED)){
            userInfoAll.setContinueWinCount(userInfoAll.getContinueWinCount() + 1);
            userInfoAll.setTotalPoints(pointComputeService.addPoints(userInfoAll.getTotalPoints(),
                    userInfoAll.getContinueWinCount()));
        }else if (result.equals(GameStatus.FAILED)){
            userInfoAll.setContinueWinCount(userInfoAll.getContinueWinCount() - 1);
            userInfoAll.setTotalPoints(pointComputeService.subPoints(userInfoAll.getTotalPoints()));
        }
        addInfo(userInfoAll);
        return userInfoAll.getTotalPoints();
    }

//    判断是否为同一个人
    public UserInfo judgeSamePerson(UserInfo userInfo){
        for(UserInfo userInfoList : userInfoLists){
            if(userInfoList.getUserId() == userInfo.getUserId()){
                userInfo.setTotalPoints(userInfoList.getTotalPoints());
                userInfo.setContinueWinCount(userInfoList.getContinueWinCount());
                userInfo.setPlayTimes(userInfoList.getPlayTimes());
                break;
            }else{
                userInfo.setTotalPoints(0);
                userInfo.setTotalPoints(0);
                userInfo.setContinueWinCount(0);
                userInfo.setPlayTimes(0);
            }
        }
    return userInfo;
    }

//   增加用户信息到列表
    public List<UserInfo> addInfo(UserInfo userInfo){
        userInfoLists.add(userInfo);
        return userInfoLists;
    }
}
