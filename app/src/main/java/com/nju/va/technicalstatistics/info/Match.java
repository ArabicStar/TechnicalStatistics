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

    private String name;
    private long id;

    private List< Game > games;

    //这个变量不需要存到数据库
    private boolean finish;

    public Match( Team a, Team b ) {
        name = "";
        games = new ArrayList< Game >( 5 );
        leftTeam = a;
        rightTeam = b;
        finish = false;
    }

    public void addGame( Game g ) {
        if( games != null && !finish ) games.add( g );
        refresh();
    }

    public Team getRightTeam() {
        return rightTeam;
    }

    public Team getLeftTeam() {
        return leftTeam;
    }

    private void refresh() { if( games.size() >= 5 ) finish = true; }

    public long getId() { return id; }

    public String getName() { return name; }

    public long getLeftTeamId() { return leftTeam.getTeamId(); }

    public long getRightTeamId() { return rightTeam.getTeamId(); }

    public boolean isFinish() { return finish; }

    public List< Game > getGames() { return new ArrayList<>( games ); }

    public void setId( long id ) { this.id = id; }

    public void setName( String name ) { this.name = name;}

    public void setFinish( boolean finish ) { this.finish = finish; }
}

