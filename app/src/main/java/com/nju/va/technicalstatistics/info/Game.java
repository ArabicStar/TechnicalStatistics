package com.nju.va.technicalstatistics.info;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.nju.va.technicalstatistics.dao.gen.DaoSession;
import com.nju.va.technicalstatistics.dao.gen.PointDao;
import com.nju.va.technicalstatistics.dao.gen.GameDao;

/**
 * Created by jqwu on 2017/2/4.
 * 一局比赛，一场比赛最多有五局，该类不出现在数据库中，对应为gno
 */

@Entity
public class Game {
    @Id
    private int gameId;
    private int matchId;
    private int gameNo;

    @ToMany(referencedJoinProperty = "gameId")
    private List<Point> leftPoints = new ArrayList<>(30);
    @ToMany(referencedJoinProperty = "gameId")
    private List<Point> rightPoints = new ArrayList<>(30);

    private boolean finish = false;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 359416843)
    private transient GameDao myDao;

    @Generated(hash = 1263143638)
    public Game(int gameId, int matchId, int gameNo, boolean finish) {
        this.gameId = gameId;
        this.matchId = matchId;
        this.gameNo = gameNo;
        this.finish = finish;
    }

    @Generated(hash = 380959371)
    public Game() {
    }

    public void addLeftPoint(Point p) {
        if (p != null && !finish) {
            leftPoints.add(p);
            p.setGameId(gameId);
        }
        tryEnd();
    }

    public void addRightPoint(Point p) {
        if (p != null && !finish) {
            rightPoints.add(p);
            p.setGameId(gameId);
        }
        tryEnd();
    }

    public List<Point> leftPoints() {
        return leftPoints;
    }

    public List<Point> rightPoints() {
        return rightPoints;
    }

    public List<Point> points() {
        List<Point> points = new ArrayList<>(leftPoints);
        points.addAll(rightPoints);
        return points;
    }

    private void tryEnd() {
        int leftSize = leftPoints.size();
        int rightSize = rightPoints.size();

        if (leftSize >= 25 && leftSize - rightSize > 1) finish = true;
        if (rightSize >= 25 && rightSize - leftSize > 1) finish = true;
    }

    public int getMatchId() {
        return this.matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getGameNo() {
        return this.gameNo;
    }

    public void setGameNo(int gameNo) {
        this.gameNo = gameNo;
    }

    public boolean getFinish() {
        return this.finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 671273091)
    public List<Point> getLeftPoints() {
        if (leftPoints == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointDao targetDao = daoSession.getPointDao();
            List<Point> leftPointsNew = targetDao._queryGame_LeftPoints(gameId);
            synchronized (this) {
                if (leftPoints == null) {
                    leftPoints = leftPointsNew;
                }
            }
        }
        return leftPoints;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 754878152)
    public synchronized void resetLeftPoints() {
        leftPoints = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1185412191)
    public List<Point> getRightPoints() {
        if (rightPoints == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            PointDao targetDao = daoSession.getPointDao();
            List<Point> rightPointsNew = targetDao._queryGame_RightPoints(gameId);
            synchronized (this) {
                if (rightPoints == null) {
                    rightPoints = rightPointsNew;
                }
            }
        }
        return rightPoints;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 220952122)
    public synchronized void resetRightPoints() {
        rightPoints = null;
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
    @Generated(hash = 733596598)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getGameDao() : null;
    }

    public int getGameId() {
        return this.gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

}
