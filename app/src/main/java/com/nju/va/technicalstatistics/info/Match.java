package com.nju.va.technicalstatistics.info;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.DaoException;

import com.nju.va.technicalstatistics.dao.gen.DaoSession;
import com.nju.va.technicalstatistics.dao.gen.TeamDao;
import com.nju.va.technicalstatistics.dao.gen.MatchDao;
import com.nju.va.technicalstatistics.dao.gen.GameDao;

/**
 * Created by jqwu on 2017/1/31.
 * 一场比赛，这个比赛里面包括了最多五局比赛（game）
 */

@Entity
public class Match implements Parcelable {
    @Id
    @Generated
    private int matchId;
    private String name = "";
    @ToOne
    private Team leftTeam;
    @ToOne
    private Team rightTeam;
    @ToMany(referencedJoinProperty = "matchId")
    private List<Game> games = new ArrayList<>(5);
    @Transient
    private boolean finish = false;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 522467795)
    private transient MatchDao myDao;

    @Generated(hash = 2048856906)
    private transient boolean leftTeam__refreshed;

    @Generated(hash = 745942319)
    private transient boolean rightTeam__refreshed;

    @Keep
    public Match(Team a, Team b) {
        leftTeam = a;
        rightTeam = b;
        finish = false;
    }

    @Generated(hash = 244888145)
    public Match(int matchId, String name) {
        this.matchId = matchId;
        this.name = name;
    }

    @Generated(hash = 1834681287)
    public Match() {
    }

    protected Match(Parcel in) {
        matchId = in.readInt();
        name = in.readString();
        leftTeam = in.readParcelable(Team.class.getClassLoader());
        rightTeam = in.readParcelable(Team.class.getClassLoader());
        finish = in.readByte() != 0;
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    private void tryEnd() {
        if (games.size() >= 5) finish = true;
    }

    public void addGame(Game g) {
        if (games != null && !finish) {
            games.add(g);
            g.setMatchId(matchId);
        }
        tryEnd();
    }

    public int getMatchId() {
        return this.matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1910526307)
    public Team getLeftTeam() {
        if (leftTeam != null || !leftTeam__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            targetDao.refresh(leftTeam);
            leftTeam__refreshed = true;
        }
        return leftTeam;
    }

    /**
     * To-one relationship, returned entity is not refreshed and may carry only the PK property.
     */
    @Generated(hash = 187561224)
    public Team peakLeftTeam() {
        return leftTeam;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1769270715)
    public void setLeftTeam(Team leftTeam) {
        synchronized (this) {
            this.leftTeam = leftTeam;
            leftTeam__refreshed = true;
        }
    }

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 235016816)
    public Team getRightTeam() {
        if (rightTeam != null || !rightTeam__refreshed) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TeamDao targetDao = daoSession.getTeamDao();
            targetDao.refresh(rightTeam);
            rightTeam__refreshed = true;
        }
        return rightTeam;
    }

    /**
     * To-one relationship, returned entity is not refreshed and may carry only the PK property.
     */
    @Generated(hash = 1295115659)
    public Team peakRightTeam() {
        return rightTeam;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 1688663072)
    public void setRightTeam(Team rightTeam) {
        synchronized (this) {
            this.rightTeam = rightTeam;
            rightTeam__refreshed = true;
        }
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
    @Generated(hash = 88911878)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMatchDao() : null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(matchId);
        dest.writeString(name);
        dest.writeParcelable(leftTeam, flags);
        dest.writeParcelable(rightTeam, flags);
        dest.writeByte((byte) (finish ? 1 : 0));
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 867250584)
    public List<Game> getGames() {
        if (games == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            GameDao targetDao = daoSession.getGameDao();
            List<Game> gamesNew = targetDao._queryMatch_Games(matchId);
            synchronized (this) {
                if (games == null) {
                    games = gamesNew;
                }
            }
        }
        return games;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1596068969)
    public synchronized void resetGames() {
        games = null;
    }
}

