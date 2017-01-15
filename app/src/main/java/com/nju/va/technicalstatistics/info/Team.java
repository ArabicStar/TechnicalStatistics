package com.nju.va.technicalstatistics.info;

import com.nju.va.technicalstatistics.R;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jqwu on 2017/1/15.
 */
public class Team {
    public String getName() {
        return name;
    }

    private String name;
    private int id;
    private int imgId;
    private Set<Member> members;

    public Team(String name){
        this.name = name;
        imgId = R.drawable.team_img;
        members = null;
    }

    public void addMember(Member m){
        if(members==null)
            members = new HashSet<Member>();
        members.add(m);
    }

    public int getImgId() {
        return imgId;
    }
}
