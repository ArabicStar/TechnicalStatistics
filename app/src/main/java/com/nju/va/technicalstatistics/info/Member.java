package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import org.greenrobot.greendao.annotation.Entity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by jqwu on 2017/1/15.
 */
@Entity
public class Member implements Parcelable {
    @IntDef({CHIEF_SPIKER, SECOND_SPIKER, LIBERO, SECOND_SETTER, CHIEF_SETTER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayerPosition {
    }

    public static final int CHIEF_SPIKER = 0x0;
    public static final int SECOND_SPIKER = 0x1;
    public static final int LIBERO = 0x2;
    public static final int SECOND_SETTER = 0x3;
    public static final int CHIEF_SETTER = 0x4;
    private static final String[] POSITION_NAME = new String[]{"主攻", "副攻", "自由人", "二传", "接应"};

    ///////////////////

    protected Member(Parcel in) {
        teamId = in.readInt();
        number = in.readInt();
        name = in.readString();
        position = in.readInt();
    }

    public Member(String name, int memberId, int position) {
        this.name = name;
        this.number = memberId;
        this.position = position;

    }

    @Generated(hash = 367284327)
    public Member() {
    }

    @Generated(hash = 137995133)
    public Member(int teamId, int number, String name, int position) {
        this.teamId = teamId;
        this.number = number;
        this.name = name;
        this.position = position;
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(teamId);
        dest.writeInt(number);
        dest.writeString(name);
        dest.writeInt(position);
    }

    public int getTeamId() {
        return this.teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getPositionString() {
        return POSITION_NAME[position];
    }

    @Id
    private int teamId;
    @Id
    private int number;
    private String name;
    @PlayerPosition
    private int position;
}
