package com.nju.va.technicalstatistics.info;

import java.io.Serializable;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Stop implements Serializable {
    private int id;
    /**
     * 暂停时，叫暂停队伍的得分
     */
    private int pointA;

    /**
     * 暂停时，另一只队伍的得分
     */
    private int pointB;

    /**
     * 哪一只队伍提出暂停
     */
    private int teamA;

    private int gameId;

    private int matchId;

    public int getId() { return id; }

    public int getPointA() { return pointA; }

    public int getPointB() { return pointB; }

    public int getStopTeam() { return teamA; }
}
