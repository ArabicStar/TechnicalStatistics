package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/31.
 */

public class Point implements Parcelable {

    public static final String[] METHOD_NAME =
            new String[]{ "扣球", "发球", "拦网", "探头", "接发球", "防守", "二传", "犯规", "其它" };
    public static final String[] METHOD_KEY =
            new String[]{ "K", "F", "L", "T", "J", "S", "E", "X", "…" };
    private long id;
    private long winnerTeamId;
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

    Point(){
        members = new ArrayList<Member>(5);
    }

    public long getId() { return id; }

    public String getMethod() { return method; }

    public String getRemark() { return remark; }

    public long getWinnerTeamId() { return winnerTeamId; }

    public boolean isActive() { return isActive; }

    public void setId( int id ) { this.id = id; }

    public List< Member > getRelMembers() { return members; }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel parcel, int i ) {
        parcel.writeLong( id );
        parcel.writeLong( winnerTeamId );
        parcel.writeTypedList( members );
        parcel.writeByte((byte)(isActive ?1:0));//boolean
        parcel.writeString(method);
        parcel.writeString(remark);
    }

    public static final Creator< Point > CREATOR = new Creator< Point >() {
        @Override public Point createFromParcel( Parcel parcel ) {
            Point point = new Point();
            point.id = parcel.readLong();
            point.winnerTeamId = parcel.readLong();
            parcel.readTypedList( point.members, Member.CREATOR );
            point.isActive = (parcel.readByte()!=0);
            point.method = parcel.readString();
            point.remark = parcel.readString();
            return point;
        }

        @Override public Point[] newArray( int i ) { return new Point[i]; }
    };
}
