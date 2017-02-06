package com.nju.va.technicalstatistics.info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/2/4.
 * 一局比赛，一场比赛最多有五局
 */

public class Game {
    private List< Point > leftPoints;
    private List< Point > rightPoints;

    /**
     * 这两个list用于记录此时上场阵容，无需保存到数据库，但需要能在业务层之间传递
     */
    private List< Member > leftMembers;
    private List< Member > rightMembers;

    private List< Stop > stops;
    private List< Exchange > exchanges;

    private boolean finish;

    public Game() {
        leftPoints = new ArrayList< Point >( 30 );
        rightPoints = new ArrayList< Point >( 30 );

        leftMembers = new ArrayList< Member >( 20 );
        rightMembers = new ArrayList< Member >( 20 );

        stops = new ArrayList< Stop >( 5 );
        exchanges = new ArrayList< Exchange >( 15 );
    }

    public void addLeftPoint( Point p ) {
        //比赛没有结束
        if (p != null && !isFinish()) leftPoints.add( p );
        refresh();
    }

    public void addRightPoint( Point p ) {
        //比赛没有结束
        if (p != null && !isFinish()) rightPoints.add( p );
        refresh();
    }

    public void changeMember( Exchange ex ) {
        //比赛没有结束
        if (ex != null && !isFinish()) exchanges.add( ex );

        int idx = leftMembers.indexOf( ex.getOldMem() );

        for (Member m : leftMembers) {
            if (m.equals( ex.getOldMem() )) {
                leftMembers.remove( m );
                leftMembers.add( ex.getNewMem() );
                return;
            }
        }

        for (Member m : rightMembers) {
            if (m.equals( ex.getOldMem() )) {
                rightMembers.remove( m );
                rightMembers.add( ex.getNewMem() );
                return;
            }
        }
    }

    public void addStop( Stop s ) {
        if (s != null && !isFinish()) stops.add( s );
    }

    public boolean isFinish() {
        return finish;
    }

    private void refresh() {
        int leftSize = leftPoints.size();
        int rightSize = rightPoints.size();

        if (leftSize >= 25 && leftSize - rightSize > 1) finish = true;
        if (rightSize >= 25 && rightSize - leftSize > 1) finish = true;
    }
}
