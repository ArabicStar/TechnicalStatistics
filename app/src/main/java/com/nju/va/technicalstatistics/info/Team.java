package com.nju.va.technicalstatistics.info;

import com.nju.va.technicalstatistics.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jqwu on 2017/1/15.
 */
public class Team implements Serializable {
    public String getName() {
        return name;
    }

    private String name;
    private int id;
    private int imgId;
    private List<Member> members;

    public List<Member> getMembers() {
        if(members==null)
            return new ArrayList<Member>();
        else
            return members;
    }



    public Team(String name){
        this.name = name;
        imgId = R.drawable.team_img;
        members = null;
    }

    public void addMember(Member m){
        if(members==null)
            members = new ArrayList<Member>();
        members.add(m);
    }

    public int getImgId() {
        return imgId;
    }
}
