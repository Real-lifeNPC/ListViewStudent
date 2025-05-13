package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private StudentAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo ListView
        listView = findViewById(R.id.listView);

        // Khởi tạo com.example.myapplication.DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Lấy danh sách sinh viên từ database
        List<Student> students = dbHelper.getAllStudents();

        // Khởi tạo và gán adapter cho ListView
        adapter = new StudentAdapter(this, students);
        listView.setAdapter(adapter);
    }
}