package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nju.va.technicalstatistics.dao.gen.DaoSession;
import com.nju.va.technicalstatistics.dao.gen.MemberPointRelDao;
import com.nju.va.technicalstatistics.dao.gen.TeamDao;
import com.nju.va.technicalstatistics.dao.gen.PointDao;

/**
 * Created by jqwu on 2017/1/31.
 */

@Entity
public class Point {
    public static final String[] METHOD_NAME =
            new String[]{"扣球", "发球", "拦网", "探头", "接发球", "防守", "二传", "犯规", "其它"};
    public static final String[] METHOD_KEY =
            new String[]{"K", "F", "L", "T", "J", "S", "E", "X", "…"};

    @Id
    private int pointId;
    private int gameId;
    private int pointNo;
    @ToOne
    private Team winner;
    @ToMany(referencedJoinProperty = "pointId")
    private List<MemberPointRel> members = new ArrayList<>(5);
    private boolean positive;//主动得分为true，被动得分为false
    private String method;//得分方式
    private String remark;//备注
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 1980395011)
    private transient PointDao myDao;

    @Generated(hash = 69179097)
    public Point(int pointId, int gameId, int pointNo, boolean positive,
                 String method, String remark) {
        this.pointId = pointId;
        this.gameId = gameId;
        this.pointNo = pointNo;
        this.positive = positive;
        this.method = method;
        this.remark = remark;
    }

    @Generated(hash = 1977038299)
    public Point() {
    }

    public int getPointId() {
        return this.pointId;
    }

    public void setPointId(int pointId) {
        this.pointId = pointId;
    }

    public int getGameId() {
        return this.gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPointNo() {
        return this.pointNo;
    }

    public void setPointNo(int pointNo) {
        this.pointNo = pointNo;
    }

    public boolean getPositive() {
        return this.positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Generated(hash = 1557179768)
    private transient boolean winner__refreshed;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 262686772)
    public Team getWinner() {
        if (winner != null || !winner__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            targetDao.refresh(winner);
            winner__refreshed = true;
        }
        return winner;
    }

    /**
     * To-one relationship, returned entity is not refreshed and may carry only the PK property.
     */
    @Generated(hash = 400474475)
    public Team peakWinner() {
        return winner;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 39840778)
    public void setWinner(Team winner) {
        synchronized (this) {
            this.winner = winner;
            winner__refreshed = true;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 808636917)
    public List<MemberPointRel> getMembers() {
        if (members == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MemberPointRelDao targetDao = daoSession.getMemberPointRelDao();
            List<MemberPointRel> membersNew = targetDao
                    ._queryPoint_Members(pointId);
            synchronized (this) {
                if (members == null) {
                    members = membersNew;
                }
            }
        }
        return members;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1358688666)
    public synchronized void resetMembers() {
        members = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1714415827)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPointDao() : null;
    }
}

