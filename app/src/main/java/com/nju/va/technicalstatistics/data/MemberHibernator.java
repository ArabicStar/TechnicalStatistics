package com.nju.va.technicalstatistics.data;

import com.nju.va.technicalstatistics.info.Member;

import java.util.Collection;
import java.util.List;

/**
 * Created by tinke on 2017/1/31.
 */

public interface MemberHibernator {
    public boolean save( Member m );

    public boolean save( Collection< Member > members );

    public boolean delete( int memberId );

    public boolean deleteByTeam( int teamId );

    public boolean update( Member m );

    public boolean saveOrUpdate( Member m );

    public Member find( int id );

    public List< Member > findByTeam( int teamId );

    public void close();
}
