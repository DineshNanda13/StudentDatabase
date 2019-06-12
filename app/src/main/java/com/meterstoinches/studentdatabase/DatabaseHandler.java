package com.meterstoinches.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public static final String database_name = "student_database";
    public static final String table_name = "student_record";

    public static final String key_student_id = "student_id";
    public static final String key_name = "name";
    public static final String key_roll_number = "roll_number";
    public static final String key_email_ID = "email_ID";

    String create_table = "create table "+table_name+"("+key_student_id+" Integer primary key,"+
            key_name+" Text,"+key_roll_number+" Integer,"+key_email_ID+" Text"+")";

    String drop_table = "drop table if exists "+table_name;


    public DatabaseHandler(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(drop_table);
        onCreate(db);
    }
    public void addStudent(String p1,String p2,String p3){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key_name,p1);
        contentValues.put(key_roll_number,p2);
        contentValues.put(key_email_ID,p3);
        db.insert(table_name,null,contentValues);
        db.close();
    }
    public List<Student> getAllStudents(){
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String selectquery = "select * from "+table_name;
        Cursor cursor = db.rawQuery(selectquery,null);
        if (cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setStudent_id(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setRoll_number(Integer.parseInt(cursor.getString(2)));
                student.setEmail_ID(cursor.getString(3));

                studentList.add(student);

            }while (cursor.moveToNext());
        }
        return studentList;
    }
    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(drop_table);
        db.execSQL(create_table);
    }
    public void deleteLastRow(){
        SQLiteDatabase db = this.getWritableDatabase();
        Student student = new Student();
        String selectQuery = "SELECT  * FROM " + table_name;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToLast()) {
            student.setStudent_id(Integer.parseInt(cursor.getString(0)));
        }

        db.execSQL("DELETE FROM " + table_name+ " WHERE "+key_student_id+"='"+student.getStudent_id()+"'");
        db.close();

    }
}
