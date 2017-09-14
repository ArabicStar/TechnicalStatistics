@file:JvmName("DaoContext")

package com.nju.va.technicalstatistics

import android.app.Application
import com.nju.va.technicalstatistics.dao.gen.DaoMaster
import com.nju.va.technicalstatistics.dao.gen.MemberDao
import com.nju.va.technicalstatistics.dao.gen.TeamDao
import com.nju.va.technicalstatistics.info.Member

/**
 * Created by tinker on 2017/9/13.
 */
object MyApplication : Application() {
    val daoSession by lazy { DaoMaster(DaoMaster.DevOpenHelper(this, "test-db").writableDb).newSession() }
}

fun getMemberDao() = MyApplication.daoSession.memberDao

fun getTeamDao() = MyApplication.daoSession.teamDao

class Team(private val teamDao: TeamDao) {
    fun getAll() = teamDao.loadAll()

    fun selectTeamById(teamId: Int) = teamDao.load(teamId)

    fun selectTeamByMember(memberId: Int) = with(teamDao.queryBuilder()) {
        join(Member::class.java, MemberDao.Properties.TeamId)
                .where(MemberDao.Properties.TeamId.eq(memberId))
        list().getOrNull(0)
    }

}

