package ro.octa.greendaosample.dao;

import android.os.Parcel;
import android.os.Parcelable;

import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table DBUSER.
 */
public class DBUser implements Parcelable {

    private Long id;
    /**
     * Not-null value.
     */
    private String email;
    /**
     * Not-null value.
     */
    private String password;
    private long detailsId;

    /**
     * Used to resolve relations
     */
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    private transient DBUserDao myDao;

    private DBUserDetails details;
    private Long details__resolvedKey;


    public DBUser() {
    }

    public DBUser(Long id) {
        this.id = id;
    }

    public DBUser(Long id, String email, String password, long detailsId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.detailsId = detailsId;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDBUserDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Not-null value.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Not-null value.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(long detailsId) {
        this.detailsId = detailsId;
    }

    /**
     * To-one relationship, resolved on first access.
     */
    public DBUserDetails getDetails() {
        long __key = this.detailsId;
        if (details__resolvedKey == null || !details__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DBUserDetailsDao targetDao = daoSession.getDBUserDetailsDao();
            DBUserDetails detailsNew = targetDao.load(__key);
            synchronized (this) {
                details = detailsNew;
                details__resolvedKey = __key;
            }
        }
        return details;
    }

    public void setDetails(DBUserDetails details) {
        if (details == null) {
            throw new DaoException("To-one property 'detailsId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.details = details;
            detailsId = details.getId();
            details__resolvedKey = detailsId;
        }
    }

    /**
     * Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context.
     */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context.
     */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /**
     * Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context.
     */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeLong(this.detailsId);
        dest.writeParcelable(this.details, 0);
        dest.writeValue(this.details__resolvedKey);
    }

    private DBUser(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.email = in.readString();
        this.password = in.readString();
        this.detailsId = in.readLong();
        this.details = in.readParcelable(DBUserDetails.class.getClassLoader());
        this.details__resolvedKey = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<DBUser> CREATOR = new Creator<DBUser>() {
        public DBUser createFromParcel(Parcel source) {
            return new DBUser(source);
        }

        public DBUser[] newArray(int size) {
            return new DBUser[size];
        }
    };
}
