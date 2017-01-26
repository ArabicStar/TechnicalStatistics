package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;

import com.nju.va.technicalstatistics.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by jqwu on 2017/1/15.
 */
public class Team implements Parcelable, Serializable {
    private String name;
    private int id;
    private int imgId;
    private List< Member > members;

    public Team( String name ) {
        this.name = name;
        this.imgId = R.drawable.team_img;
        this.members = new ArrayList<>( 20 );
    }

    public String getName() { return name; }

    public int getImgId() {
        return imgId;
    }

    public List< Member > getMembers() { return new ArrayList<>( members ); }

    public void addMember( Member m ) {members.add( m ); }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel( Parcel parcel, int i ) {
        parcel.writeString( name );
        parcel.writeInt( id );
        parcel.writeInt( imgId );
        parcel.writeParcelableArray( members.toArray( new Member[members.size()] ), 0 );
    }

    public static final Creator< Team > CREATOR = new Creator< Team >() {
        @Override public Team createFromParcel( Parcel parcel ) {
            Team team = new Team( parcel.readString() );
            team.id = parcel.readInt();
            team.imgId = parcel.readInt();
            team.members = parcel.readArrayList( ClassLoader.getSystemClassLoader() );
            return team;
        }

        @Override public Team[] newArray( int i ) { return new Team[i]; }
    };
}
