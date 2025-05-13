package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_AVATAR = "avatar";
    private static final String KEY_FULL_NAME = "full_name";
    private static final String KEY_STUDENT_ID = "student_id";
    private static final String KEY_BIRTH_DATE = "birth_date";
    private static final String KEY_CLASS_NAME = "class_name";
    private static final String KEY_MAJOR = "major";
    private static final String KEY_SPECIALIZATION = "specialization";
    private static final String KEY_HOMETOWN = "hometown";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_AVATAR + " INTEGER,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_STUDENT_ID + " TEXT,"
                + KEY_BIRTH_DATE + " TEXT,"
                + KEY_CLASS_NAME + " TEXT,"
                + KEY_MAJOR + " TEXT,"
                + KEY_SPECIALIZATION + " TEXT,"
                + KEY_HOMETOWN + " TEXT" + ")";
        db.execSQL(CREATE_STUDENTS_TABLE);

        // Thêm dữ liệu mẫu
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Dữ liệu mẫu cũ
        insertStudent(db, R.drawable.avatar1, "Nguyễn Minh Trí", "21200301", "01/01/2003", "DTVT1",
                "Điện tử - Viễn thông", "Điện tử", "Hà Nội");
        insertStudent(db, R.drawable.avatar2, "Trần Thị Thu Thủy", "21200302", "02/02/2003", "DTVT1",
                "Điện tử - Viễn thông", "Máy tính - Nhúng", "TP.HCM");
        insertStudent(db, R.drawable.avatar3, "Trần Bảo Phúc", "21200303", "03/03/2003", "DTVT2",
                "Điện tử - Viễn thông", "Máy tính - Nhúng", "Đà Nẵng");

        // 5 dữ liệu mẫu mới
        insertStudent(db, R.drawable.avatar4, "Phạm Thị Dung", "21200304", "24/04/2003", "DTVT1",
                "Điện tử - Viễn thông", "Viễn thông", "Hải Phòng");
        insertStudent(db, R.drawable.avatar5, "Phạm Thị Mỹ Phương", "21200305", "25/05/2003", "DTVT2",
                "Điện tử - Viễn thông", "Điện tử", "Cần Thơ");
        insertStudent(db, R.drawable.avatar6, "Nguyễn Duy Phúc", "21200306", "16/06/2003", "DTVT2",
                "Điện tử - Viễn thông", "Viễn thông", "Huế");
        insertStudent(db, R.drawable.avatar7, "Trần Huỳnh Tín", "21200307", "07/07/2003", "DTVT2",
                "Điện tử - Viễn thông", "Máy tính - Nhúng", "Quảng Ninh");
        insertStudent(db, R.drawable.avatar8, "Đỗ Thị Hà", "21200308", "28/08/2003", "DTVT1",
                "Điện tử - Viễn thông", "Viễn thông", "Bắc Ninh");
    }

    private void insertStudent(SQLiteDatabase db, int avatar, String fullName, String studentId,
                               String birthDate, String className, String major, String specialization,
                               String hometown) {
        ContentValues values = new ContentValues();
        values.put(KEY_AVATAR, avatar);
        values.put(KEY_FULL_NAME, fullName);
        values.put(KEY_STUDENT_ID, studentId);
        values.put(KEY_BIRTH_DATE, birthDate);
        values.put(KEY_CLASS_NAME, className);
        values.put(KEY_MAJOR, major);
        values.put(KEY_SPECIALIZATION, specialization);
        values.put(KEY_HOMETOWN, hometown);
        db.insert(TABLE_STUDENTS, null, values);
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENTS, null);

        if (cursor.moveToFirst()) {
            do {
                int avatar = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_AVATAR));
                String fullName = cursor.getString(cursor.getColumnIndexOrThrow(KEY_FULL_NAME));
                String studentId = cursor.getString(cursor.getColumnIndexOrThrow(KEY_STUDENT_ID));
                String birthDate = cursor.getString(cursor.getColumnIndexOrThrow(KEY_BIRTH_DATE));
                String className = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CLASS_NAME));
                String major = cursor.getString(cursor.getColumnIndexOrThrow(KEY_MAJOR));
                String specialization = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SPECIALIZATION));
                String hometown = cursor.getString(cursor.getColumnIndexOrThrow(KEY_HOMETOWN));
                students.add(new Student(avatar, fullName, studentId, birthDate, className, major, specialization, hometown));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return students;
    }
}