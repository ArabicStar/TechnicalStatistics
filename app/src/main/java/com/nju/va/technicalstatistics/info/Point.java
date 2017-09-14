package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;

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

    private int gno;//1~5
    private int mtid;
    private int pno;//该局比赛的第几分
    private int winnerTeamId;
    private int[] members;//每一分有一到三人为其作出贡献，保存他们的id，即tid*100+mno
    private boolean isActive;//主动得分为true，被动得分为false
    private String method;//得分方式
    private String remark;//备注

    public Point(){
        members = new int[5];
    }

    public String getMethod() { return method; }

    public String getRemark() { return remark; }

    public int getWinnerTeamId() { return winnerTeamId; }

    public boolean isActive() { return isActive; }

    public int[] getRelMembers() { return members; }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel(Parcel parcel, int i ) {
        parcel.writeInt( winnerTeamId );
        parcel.writeIntArray( members );
        parcel.writeByte((byte)(isActive ?1:0));//boolean
        parcel.writeString(method);
        parcel.writeString(remark);
    }

    public static final Creator< Point > CREATOR = new Creator< Point >() {
        @Override public Point createFromParcel( Parcel parcel ) {
            Point point = new Point();
            point.winnerTeamId = parcel.readInt();
            parcel.readIntArray(point.members);
            point.isActive = (parcel.readByte()!=0);
            point.method = parcel.readString();
            point.remark = parcel.readString();
            return point;
        }

        @Override public Point[] newArray( int i ) { return new Point[i]; }
    };

    public void setActive( boolean active ) { this.isActive = active; }

    public void setMethod( String method ) { this.method = method; }

    public void setRemark( String remark ) { this.remark = remark; }

    public void setWinnerTeam( int winnerTeam ) { this.winnerTeamId = winnerTeam; }

    public void setMember( int[] members ) { this.members = members;}

    public int getGno() {
        return gno;
    }

    public void setGno(int gno) {
        this.gno = gno;
    }

    public int getMtid() {
        return mtid;
    }

    public void setMtid(int mtid) {
        this.mtid = mtid;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
}
