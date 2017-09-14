package com.nju.va.technicalstatistics.info;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by tinker on 2017/9/14.
 */

@Entity
public class MemberPointRel {
    @Id
    private int memberId;
    @Id
    private int pointId;
    @Generated(hash = 1137627264)
    public MemberPointRel(int memberId, int pointId) {
        this.memberId = memberId;
        this.pointId = pointId;
    }
    @Generated(hash = 1237909001)
    public MemberPointRel() {
    }
    public int getMemberId() {
        return this.memberId;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public int getPointId() {
        return this.pointId;
    }
    public void setPointId(int pointId) {
        this.pointId = pointId;
    }
}
