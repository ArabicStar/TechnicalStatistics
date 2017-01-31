package com.nju.va.technicalstatistics.info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Match {
    List<Point> points;

    Team leftTeam;
    Team rightTeam;

    public List<Point> getPoints(Team t){
        if(!t.equals(leftTeam)&&!t.equals(rightTeam)){
            return null;
        }else{
            List<Point> p = new ArrayList<Point>();
            for (Point point:points) {
                if(point.getTeam().equals(t)){
                    p.add(point);
                }
            }

            return p;
        }
    }

}
