package com.example.giangdam.greendaoex.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.example.giangdam.greendaoex.dao.DBPhoneNumber;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBPHONE_NUMBER".
*/
public class DBPhoneNumberDao extends AbstractDao<DBPhoneNumber, Long> {

    public static final String TABLENAME = "DBPHONE_NUMBER";

    /**
     * Properties of entity DBPhoneNumber.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PhoneNumber = new Property(1, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property DetailsId = new Property(2, long.class, "detailsId", false, "DETAILS_ID");
    };

    private Query<DBPhoneNumber> dBUserDetails_PhoneNumbersQuery;

    public DBPhoneNumberDao(DaoConfig config) {
        super(config);
    }
    
    public DBPhoneNumberDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBPHONE_NUMBER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PHONE_NUMBER\" TEXT NOT NULL UNIQUE ," + // 1: phoneNumber
                "\"DETAILS_ID\" INTEGER NOT NULL );"); // 2: detailsId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBPHONE_NUMBER\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBPhoneNumber entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getPhoneNumber());
        stmt.bindLong(3, entity.getDetailsId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DBPhoneNumber readEntity(Cursor cursor, int offset) {
        DBPhoneNumber entity = new DBPhoneNumber( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // phoneNumber
            cursor.getLong(offset + 2) // detailsId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBPhoneNumber entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPhoneNumber(cursor.getString(offset + 1));
        entity.setDetailsId(cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DBPhoneNumber entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DBPhoneNumber entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "phoneNumbers" to-many relationship of DBUserDetails. */
    public List<DBPhoneNumber> _queryDBUserDetails_PhoneNumbers(long detailsId) {
        synchronized (this) {
            if (dBUserDetails_PhoneNumbersQuery == null) {
                QueryBuilder<DBPhoneNumber> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.DetailsId.eq(null));
                dBUserDetails_PhoneNumbersQuery = queryBuilder.build();
            }
        }
        Query<DBPhoneNumber> query = dBUserDetails_PhoneNumbersQuery.forCurrentThread();
        query.setParameter(0, detailsId);
        return query.list();
    }

}
