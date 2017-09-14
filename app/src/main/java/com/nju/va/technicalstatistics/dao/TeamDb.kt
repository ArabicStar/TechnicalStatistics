package com.nju.va.technicalstatistics.dao

import com.nju.va.technicalstatistics.dao.gen.MemberDao
import com.nju.va.technicalstatistics.dao.gen.TeamDao
import com.nju.va.technicalstatistics.info.Member
import com.nju.va.technicalstatistics.info.Team

class TeamDb(private val teamDao: TeamDao) {

    fun getAll() = teamDao.loadAll()

    fun selectTeamById(teamId: Int) = teamDao.load(teamId)

    fun selectTeamByMember(memberId: Int) = with(teamDao.queryBuilder()) {
        join(Member::class.java, MemberDao.Properties.TeamId)
        where(MemberDao.Properties.TeamId.eq(memberId))
        list().getOrNull(0)
    }

    fun add(team: Team) = teamDao.save(team)

    fun delete(teamId: Int) = teamDao.update(teamDao.load(teamId).apply { valid = false })

    fun update(team: Team) = teamDao.update(team)
}