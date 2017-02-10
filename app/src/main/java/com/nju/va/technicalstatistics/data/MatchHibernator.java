package com.nju.va.technicalstatistics.data;

import com.nju.va.technicalstatistics.info.Match;

import java.util.List;

/**
 * Created by tinke on 2017/2/6.
 */

public interface MatchHibernator {
    public boolean save( Match match );

    public boolean delete( long id );

    public Match find( long id );

    public Match find( long id, boolean ignoreDeleted );

    public List< Match > find( String name );
}
