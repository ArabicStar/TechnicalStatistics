package com.nju.va.technicalstatistics.dao

import com.nju.va.technicalstatistics.dao.gen.GameDao
import com.nju.va.technicalstatistics.dao.gen.MemberPointRelDao
import com.nju.va.technicalstatistics.dao.gen.PointDao
import com.nju.va.technicalstatistics.info.MemberPointRel
import com.nju.va.technicalstatistics.info.Point

/**
 * Created by tinker on 2017/9/14.
 */
class PointDb(private val dao: PointDao, private val gameDao: GameDao, private val relDao: MemberPointRelDao) {
    fun selectByMatch(matchId: Int) =
            gameDao.queryBuilder().where(GameDao.Properties.MatchId.eq(matchId))
                    .list().flatMap { it.points() }

    fun selectByMember(memberId: Int) = with(dao.queryBuilder()) {
        join(MemberPointRel::class.java, MemberPointRelDao.Properties.PointId)
        where(MemberPointRelDao.Properties.MemberId.eq(memberId))
        list()
    }

    fun add(point: Point) = dao.save(point)

    fun delete(pointId: Int) = dao.deleteByKey(pointId)

    fun update(point: Point) = dao.update(point)
}