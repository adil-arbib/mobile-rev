package com.arbib.test003.repositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.arbib.test003.models.Note;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes_db";
    private static final String TABLE_NOTE = "notes";
    private static final String COLUMN_NOTE_ID = "id";
    private static final String COLUMN_NOTE_TITLE = "title";
    private static final String COLUMN_NOTE_CONTENT= "content";

    private static final int VERSION = 1;

    private Context context;

    public MyDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NOTE
                +"("+COLUMN_NOTE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +COLUMN_NOTE_TITLE+" TEXT,"
                +COLUMN_NOTE_CONTENT+" TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE_TITLE, note.getTitle());
        contentValues.put(COLUMN_NOTE_CONTENT, note.getContent());
        db.insert(TABLE_NOTE, null, contentValues);
        db.close();
        Toast.makeText(context, "Added Successfully !!", Toast.LENGTH_SHORT).show();
    }

    public void deleteNote(Integer noteId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NOTE, COLUMN_NOTE_ID+"="+noteId, null);
        db.close();
    }


    public List<Note> allNotes() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {COLUMN_NOTE_ID,COLUMN_NOTE_TITLE,COLUMN_NOTE_TITLE};
        Cursor cursor = db.query(TABLE_NOTE, columns, null, null, null, null, null);
        List<Note> notes = new ArrayList<>();
        if(cursor == null) return notes;
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            notes.add(new Note(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                    ));
        }
        return notes;
    }

}
