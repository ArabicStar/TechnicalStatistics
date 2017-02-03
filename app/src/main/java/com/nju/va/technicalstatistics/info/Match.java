package com.nju.va.technicalstatistics.info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Match {
    private List<Point> leftPoints;
    private List<Point> rightPoints;

    private Team leftTeam;
    private Team rightTeam;

    private List<Stop> stops;
    private List<Exchange> exchanges;

    private boolean finish;

    Match(Team a,Team b){
        leftTeam = a;
        rightTeam = b;
        leftPoints = new ArrayList<Point>();
        rightPoints = new ArrayList<Point>();
        finish = false;
    }

    public void addLeftPoint(Point p){
        //比赛没有结束
        if(leftPoints!=null&&!isFinish())
            leftPoints.add(p);

    }

    public void addRightPoint(Point p){
        //比赛没有结束
        if(rightPoints!=null&&!isFinish())
            rightPoints.add(p);

    }

    public boolean isFinish(){
        if(finish)
            return true;

        int leftSize = leftPoints.size();
        int rightSize = rightPoints.size();

        if(leftSize>=25&&leftSize-rightSize>1) {
            finish = true;
            return true;
        }

        if(rightSize>=25&&rightSize-leftSize>1) {
            finish = true;
            return true;
        }

        return false;
    }

}

