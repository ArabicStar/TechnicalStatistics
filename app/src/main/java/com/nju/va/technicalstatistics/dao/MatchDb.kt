package com.nju.va.technicalstatistics.dao

import com.nju.va.technicalstatistics.dao.gen.MatchDao
import com.nju.va.technicalstatistics.info.Match

/**
 * Created by tinker on 2017/9/14.
 */
class MatchDb(private val dao: MatchDao) {
    fun getAll() = dao.loadAll()

    fun selectMatchById(matchId: Int) = dao.load(matchId)

    fun selectMatchByTeam(teamId: Int) = dao.queryBuilder()
            .whereOr(MatchDao.Properties.LeftTeam.eq(teamId), MatchDao.Properties.RightTeam.eq(teamId))
            .list()

    fun add(match: Match) = dao.save(match)

    fun delete(matchId: Int) = dao.deleteByKey(matchId)

    fun update(match: Match) = dao.update(match)
}