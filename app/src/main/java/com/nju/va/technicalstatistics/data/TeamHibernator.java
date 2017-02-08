package com.nju.va.technicalstatistics.data;

import com.nju.va.technicalstatistics.info.Member;
import com.nju.va.technicalstatistics.info.Team;

import java.util.List;

/**
 * Interface for persistence of {@link Team}.<br/>
 *
 * @see com.nju.va.technicalstatistics.data
 */
public interface TeamHibernator {
    /**
     * Persist a team entity.<br/>
     *
     * @param team to be persisted entity
     */
    public boolean save( Team team );

    /**
     * Delete a team record by id.<br/>
     *
     * @param teamId the team id
     * @return result of operation
     */
    public boolean delete( int teamId );

    /**
     * Update a persisted team entity specified by the argument's id.<br/>
     *
     * @param team the team
     * @return result of operation
     */
    public boolean update( Team team );

    /**
     * Find team by id, with ignoring all invalid entities.<br/>
     *
     * @param id team id
     * @return team instance with relevant {@link Member} instances loaded already.
     */
    public Team find( int id );

    /**
     * Find team by id.<br/>
     *
     * @param id            the id
     * @param ignoreDeleted the ignore deleted
     * @return the team
     */
    public Team find( int id, boolean ignoreDeleted );

    /**
     * Find by name list.
     *
     * @param keyword the keyword in name
     * @return the list of team
     */
    public List< Team > findByName( String keyword );

    /**
     * Close connection.
     */
    public void close();
}
