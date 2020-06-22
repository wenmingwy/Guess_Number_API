package com.twschool.practice.service;

public class PointComputeService {
//    private int continueGetPoints = 0 ;
//    private int points = 0 ;
//    当获胜加分
    public int addPoints(int points,int continueGetPoints){
        points += 3;
        if(continueGetPoints % 3 == 0){
            points += 2;
        }else if(continueGetPoints % 5 == 0){
            points += 3;
        }
        return points ;
    }
//输掉比赛减分
    public int subPoints(int points){
        points -= 3;
        return points;
    }

}
