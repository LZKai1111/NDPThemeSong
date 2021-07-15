package sg.edu.rp.c346.id20014063.ndpthemesong;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="NDPsongs.db";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_SONG = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS="singer";
    private static final String COLUMN_YEARS="years";
    private static final String COLUMN_STARS="stars";

    public DBHelper(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSql ="CREATE TABLE "+TABLE_SONG+" ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEARS + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createSongTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public long insertSong(String title, String singer, int years, int stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_SINGERS, singer);
        contentValues.put(COLUMN_YEARS, years);
        contentValues.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_SONG, null, contentValues);
        db.close();
        return result;
    }

    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> songs = new ArrayList<Song>();

        //String selectQuery = "SELECT "+COLUMN_TITLE+","+COLUMN_SINGERS+","+COLUMN_YEARS+","+COLUMN_STARS+" FROM "+TABLE_SONG;
        String selectQuery = "SELECT * FROM "+TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(title, singer, year, stars);
                songs.add(song);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return songs;
    }

    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEARS, data.getYears());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + " = ?";

        String[] args = {String.valueOf(data.getId())};

        int result = db.update(TABLE_SONG, values, condition, args);

        db.close();
        return result;

    }

    public int deleteSong(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }


}
