@file:JvmName("DaoContext")

package com.nju.va.technicalstatistics

import android.app.Application
import com.nju.va.technicalstatistics.dao.TeamDb
import com.nju.va.technicalstatistics.dao.gen.DaoMaster

/**
 * Created by tinker on 2017/9/13.
 */
object MyApplication : Application() {
    val daoSession by lazy { DaoMaster(DaoMaster.DevOpenHelper(this, "test-db").writableDb).newSession() }
}

val teamDb by lazy { TeamDb(MyApplication.daoSession.teamDao) }

