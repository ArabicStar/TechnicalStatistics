package com.nju.va.technicalstatistics.info;

import java.io.Serializable;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Stop implements Serializable {
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
}
