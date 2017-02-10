package com.nju.va.technicalstatistics.info;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jqwu on 2017/2/4.
 * 一局比赛，一场比赛最多有五局
 */

public class Game {
    private List< Point > leftPoints;
    private List< Point > rightPoints;


    private boolean finish;
    private long id;

    public Game() {
        leftPoints = new ArrayList< Point >( 30 );
        rightPoints = new ArrayList< Point >( 30 );

    }

    public void addLeftPoint( Point p ) {
        //比赛没有结束
        if( p != null && !isFinish() ) leftPoints.add( p );
        refresh();
    }

    public void addRightPoint( Point p ) {
        //比赛没有结束
        if( p != null && !isFinish() ) rightPoints.add( p );
        refresh();
    }

    public List< Point > getLeftPoints() { return new ArrayList<>( leftPoints ); }

    public List< Point > getRightPoints() { return new ArrayList<>( rightPoints );}

    public boolean isFinish() {
        return finish;
    }

    private void refresh() {
        int leftSize = leftPoints.size();
        int rightSize = rightPoints.size();

        if( leftSize >= 25 && leftSize - rightSize > 1 ) finish = true;
        if( rightSize >= 25 && rightSize - leftSize > 1 ) finish = true;
    }

    public long getId() { return id; }

    public void setFinish( boolean finish ) { this.finish = finish; }

    public void setId( long id ) { this.id = id; }
}
