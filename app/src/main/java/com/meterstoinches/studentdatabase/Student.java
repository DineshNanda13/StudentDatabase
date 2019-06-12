package com.meterstoinches.studentdatabase;

public class Student {
    int student_id;
    String name;
    int roll_number;
    String email_ID;

    public Student() {}

    public Student(int student_id, String name, int roll_number, String email_ID) {
        this.student_id = student_id;
        this.name = name;
        this.roll_number = roll_number;
        this.email_ID = email_ID;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoll_number() {
        return roll_number;
    }

    public void setRoll_number(int roll_number) {
        this.roll_number = roll_number;
    }

    public String getEmail_ID() {
        return email_ID;
    }

    public void setEmail_ID(String email_ID) {
        this.email_ID = email_ID;
    }
}
