package com.nju.va.technicalstatistics.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Match implements Serializable {


    private Team leftTeam;
    private Team rightTeam;

    private String name;

    private boolean finish;

    public Match(Team a,Team b){
        name = null;
        leftTeam = a;
        rightTeam = b;
        finish = false;
    }
    public Team getRightTeam() {
        return rightTeam;
    }
    public Team getLeftTeam() {
        return leftTeam;
    }



}

