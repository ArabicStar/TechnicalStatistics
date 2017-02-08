package com.nju.va.technicalstatistics.info;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Point implements Serializable {

    public static final String[] METHOD_NAME = new String[]{ "扣球", "发球", "拦网", "探头", "接发球", "防守", "二传", "犯规","其它"};
    public static final String[] METHOD_KEY = new String[]{ "K", "F", "L", "T", "J", "S", "E", "X","…"};
    private int id;
    /**
     * 每一分有一到三人为其作出贡献
     */
    private List< Member > members;//直接存member是不是不太好，还是直接存id?

    /**
     * 主动得分为true，被动得分为false
     */
    private boolean isActive;

    /**
     * 得分方式，如：进攻等
     */
    private String method;

    /**
     * 备注
     */
    private String remark;

    public int getId() { return id; }

    public String getMethod(){return method;}

    public void setId( int id ) { this.id = id; }
}
