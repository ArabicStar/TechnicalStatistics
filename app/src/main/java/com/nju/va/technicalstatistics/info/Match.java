package com.nju.va.technicalstatistics.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Match implements Serializable {
    private List<Point> leftPoints;
    private List<Point> rightPoints;

    /**
     * 这两个list用于记录此时上场阵容，无需保存到数据库，但需要能在业务层之间传递
     */
    private List<Member> leftMembers;
    private List<Member> rightMembers;

    private Team leftTeam;
    private Team rightTeam;

    private List<Stop> stops;
    private List<Exchange> exchanges;

    private boolean finish;

    public Match(Team a,Team b){
        leftTeam = a;
        rightTeam = b;
        leftPoints = new ArrayList<Point>();
        rightPoints = new ArrayList<Point>();
        finish = false;
    }
    public Team getRightTeam() {
        return rightTeam;
    }
    public Team getLeftTeam() {
        return leftTeam;
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

