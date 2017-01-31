package com.nju.va.technicalstatistics.data;

import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.List;

/**
 * Created by tinke on 2017/1/31.
 */

public interface TeamHibernator {
    public boolean save( Team m );

    public boolean delete( int teamId );

    public boolean update( Team m );

    public Team find( int id );

    public void close();
}
