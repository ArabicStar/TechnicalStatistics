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

    public boolean delete( long memberId );

    public boolean deleteByTeam( long teamId );

    public boolean update( Member m );

    public boolean saveOrUpdate( Member m );

    public Member find( long id );

    public List< Member > findByTeam( long teamId );

    public void close();
}
