package com.example.myapplication;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private final Context context;
    private final List<Student> students;

    public StudentAdapter(Context context, List<Student> students) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.avatarImage = convertView.findViewById(R.id.avatarImg);
            viewHolder.fullName = convertView.findViewById(R.id.fullName);
            viewHolder.studentId = convertView.findViewById(R.id.studentId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = students.get(position);
        viewHolder.avatarImage.setImageResource(student.getAvatar());
        viewHolder.fullName.setText(student.getFullName());
        viewHolder.studentId.setText(student.getStudentId());

        // Thêm GestureDetector để xử lý nháy đúp
        GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                showStudentDetails(student);
                return true;
            }
        });

        convertView.setOnTouchListener((v, event) -> {
            gestureDetector.onTouchEvent(event);
            return true;
        });

        return convertView;
    }

    private void showStudentDetails(Student student) {
        new AlertDialog.Builder(context)
                .setTitle(student.getFullName())
                .setMessage("MSSV: " + student.getStudentId() + "\n\nNgày sinh: " + student.getBirthDate() +
                        "\n\nLớp: " + student.getClassName() + "\n\nNgành: " + student.getMajor() +
                        "\n\nChuyên ngành: " + student.getSpecialization() + "\n\nQuê quán: " + student.getHometown())
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .setCancelable(true)
                .show();
    }

    private static class ViewHolder {
        ImageView avatarImage;
        TextView fullName;
        TextView studentId;
    }
}