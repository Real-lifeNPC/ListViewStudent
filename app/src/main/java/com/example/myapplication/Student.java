package com.example.myapplication;

public class Student {
    private int avatar;
    private String fullName;
    private String studentId;
    private String birthDate;
    private String className;
    private String major;
    private String specialization;
    private String hometown;

    public Student(int avatar, String fullName, String studentId, String birthDate, String className,
                   String major, String specialization, String hometown) {
        this.avatar = avatar;
        this.fullName = fullName;
        this.studentId = studentId;
        this.birthDate = birthDate;
        this.className = className;
        this.major = major;
        this.specialization = specialization;
        this.hometown = hometown;
    }

    public int getAvatar() {
        return avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getClassName() {
        return className;
    }

    public String getMajor() {
        return major;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getHometown() {
        return hometown;
    }
}