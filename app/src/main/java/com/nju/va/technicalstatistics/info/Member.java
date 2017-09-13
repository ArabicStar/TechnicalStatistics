package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jqwu on 2017/1/15.
 */
public class Member implements Parcelable, Serializable {
    @IntDef( { CHIEF_SPIKER, SECOND_SPIKER, LIBERO, SECOND_SETTER, CHIEF_SETTER } )
    @Retention( RetentionPolicy.SOURCE )
    public @interface PlayerPosition { }

    public static final int CHIEF_SPIKER = 0x0;
    public static final int SECOND_SPIKER = 0x1;
    public static final int LIBERO = 0x2;
    public static final int SECOND_SETTER = 0x3;
    public static final int CHIEF_SETTER = 0x4;
    private static final String[] POSITION_NAME = new String[]{ "主攻", "副攻", "自由人", "二传", "接应" };

    private int teamId;
    private int mno;
    private String name;
    @PlayerPosition private int position;

    public Member( String name, int number, @PlayerPosition int position ) {
        this.name = name;
        this.mno = number;
        this.position = position;
    }

    public void setTeam( int teamId ) { this.teamId = teamId; }

    public int getMno() { return mno; }

    public String getName() { return name; }

    @PlayerPosition public int getPosition() { return position; }

    public int getTeam() { return teamId; }

    public String getPositionString() { return POSITION_NAME[position]; }

    @Override public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !( o instanceof Member ) ) return false;

        Member member = (Member) o;

        return teamId == member.teamId && mno == member.mno;

    }

    @Override public int hashCode() {
        int result = (int) ( teamId ^ ( teamId >>> 32 ) );
        result = 31 * result + mno;
        return result;
    }

    public static final Creator< Member > CREATOR = new Creator< Member >() {
        @Override public Member createFromParcel( Parcel parcel ) {
            @Member.PlayerPosition int pos = parcel.readInt();
            String name = parcel.readString();
            int num = parcel.readInt();
            Member member = new Member( name, num, pos );
            member.teamId = parcel.readInt();
            return member;
        }

        @Override public Member[] newArray( int i ) { return new Member[i]; }
    };

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel( Parcel parcel, int i ) {
        parcel.writeInt( position );
        parcel.writeString( name );
        parcel.writeInt(mno);
        parcel.writeLong( teamId );
    }
}
