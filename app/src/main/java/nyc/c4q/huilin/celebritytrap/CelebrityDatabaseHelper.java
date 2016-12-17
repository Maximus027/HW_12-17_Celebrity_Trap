package nyc.c4q.huilin.celebritytrap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nyc.c4q.huilin.celebritytrap.model.Celebrity;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

/**
 * Created by huilin on 12/12/16.
 */
public class CelebrityDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "celeb.db";
    private static final int DATABASE_VERSION = 2;

    private static CelebrityDatabaseHelper instance;

    public static synchronized CelebrityDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new CelebrityDatabaseHelper(context.getApplicationContext());
        }

        return instance;
    }

    public CelebrityDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static {
        cupboard().register(Celebrity.class);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();

    }
}
