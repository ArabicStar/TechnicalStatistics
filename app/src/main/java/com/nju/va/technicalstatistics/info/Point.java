package com.nju.va.technicalstatistics.info;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Point implements Serializable {
    /**
     * 每一分有一到三人为其作出贡献
     */
    private List<Member> members;//直接存member是不是不太好，还是直接存id?

    /**
     * 主动得分为true，被动得分为false
     */
    private boolean isActive;

    /**
     * 得分方式，如：进攻等
     */
    private String why;

}
