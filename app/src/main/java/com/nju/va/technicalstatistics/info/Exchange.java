package com.nju.va.technicalstatistics.info;

import java.io.Serializable;

/**
 * Created by jqwu on 2017/1/31.
 * 记录换人信息
 */

public class Exchange implements Serializable {
    /**
     * 提出换人的队伍分数
     */
    private int pointA;

    /**
     * 另一只队伍的分数
     */
    private int pointB;

    /**
     * 换上场的队员
     */
    private Member newMem;

    /**
     * 下场的队员
     */
    private Member oldMem;

    public Member getOldMem() {
        return oldMem;
    }

    public void setOldMem( Member oldMem ) {
        this.oldMem = oldMem;
    }

    public Member getNewMem() {
        return newMem;
    }

    public void setNewMem( Member newMem ) {
        this.newMem = newMem;
    }
}
