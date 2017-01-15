package com.nju.va.technicalstatistics.info;

import java.io.Serializable;

/**
 * Created by jqwu on 2017/1/15.
 */
public class Member implements Serializable {
    private int teamId;
    private int id;
    private int number;
    private String name;
    private POSITION position;

    public Member(String name, int number, POSITION position) {
        this.name = name;
        this.number = number;
        this.position = position;
    }

    /**
     * 当两个队员的所属队伍一样，号码一样时，判定为同一队员
     * @return
     */
    @Override
    public int hashCode() {
        return teamId * number;
    }

    @Override
    public boolean equals(Object obj) {
        Member other = (Member) obj;
        return this.teamId == other.teamId && this.number == other.number;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public POSITION getPosition() {
        return position;
    }
}
