package com.nju.va.technicalstatistics.info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 * 一场比赛，这个比赛里面包括了最多五局比赛（game）
 */

public class Match implements Serializable {


    private Team leftTeam;
    private Team rightTeam;

    private List< Game > games;

    private String name;

    private boolean finish;

    public Match( Team a, Team b ) {
        name = "";
        games = new ArrayList< Game >( 5 );
        leftTeam = a;
        rightTeam = b;
        finish = false;
    }

    public void addGame( Game g ) {
        if (games != null && !finish) games.add( g );
        refresh();
    }

    public String name() { return name; }

    public Team getRightTeam() {
        return rightTeam;
    }

    public Team getLeftTeam() {
        return leftTeam;
    }

    private void refresh() { if (games.size() >= 5) finish = true; }

}

