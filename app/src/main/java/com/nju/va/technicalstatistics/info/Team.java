package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;

import com.nju.va.technicalstatistics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jqwu on 2017/1/15.
 */
public class Team implements Parcelable {
    private String name;
    private int id;
    private int imgId;
    private List< Member > members;

    public Team() {
        this.imgId = R.drawable.team_img;
        this.members = new ArrayList<>( 20 );
    }

    public Team( String name ) {
        this();
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public int getImgId() {
        return imgId;
    }

    public List< Member > getMembers() { return new ArrayList<>( members ); }

    public List< Member > getOwnMembers() { return members; }

    public void setId( int id ) { this.id = id; }

    public void setName( String name ) { this.name = name; }

    public void setImgId( int imgId ) { this.imgId = imgId; }

    public void addMember( Member m ) {
        members.add( m );
        m.setTeam( id );
    }

    @Override public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !( o instanceof Team ) ) return false;

        Team team = (Team) o;

        return id == team.id;

    }

    @Override public int hashCode() {
        return (int) ( id ^ ( id >>> 32 ) );
    }

    @Override public int describeContents() { return 0; }

    @Override public void writeToParcel( Parcel parcel, int i ) {
        parcel.writeString( name );
        parcel.writeLong( id );
        parcel.writeInt( imgId );
        parcel.writeTypedList( members );
    }

    public static final Creator< Team > CREATOR = new Creator< Team >() {
        @Override public Team createFromParcel( Parcel parcel ) {
            Team team = new Team();
            team.name = parcel.readString();
            team.id = parcel.readInt();
            team.imgId = parcel.readInt();
            parcel.readTypedList( team.members, Member.CREATOR );
            return team;
        }

        @Override public Team[] newArray( int i ) { return new Team[i]; }
    };
}
